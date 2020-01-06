package com.yaj.jaso.business.worktask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.examples.PushExample;
import com.yaj.examples.PushModel;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemFind;
import com.yaj.jaso.business.measureproblem.service.MeasureProblemService;
import com.yaj.jaso.business.measureproblemuser.entity.po.MeasureProblemUserPO;
import com.yaj.jaso.business.message.entity.po.MessagePO;
import com.yaj.jaso.business.message.service.MessageService;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckFind;
import com.yaj.jaso.business.qualitycheck.service.QualityCheckService;
import com.yaj.jaso.business.qualitycheckusers.entity.po.QualityCheckUsersPO;
import com.yaj.jaso.business.reply.entity.po.ReplyPO;
import com.yaj.jaso.business.reply.service.ReplyService;
import com.yaj.jaso.business.securitycheck.entity.vo.SecurityCheckFind;
import com.yaj.jaso.business.securitycheck.service.SecurityCheckService;
import com.yaj.jaso.business.worktask.entity.po.WorkTaskPO;
import com.yaj.jaso.business.worktask.entity.vo.WorkAllOptions;
import com.yaj.jaso.business.worktask.entity.vo.WorkAllResult;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskAdd;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskCheck;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskFind;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskResult;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskVo;
import com.yaj.jaso.business.worktask.mapper.WorkTaskMapper;
import com.yaj.jaso.business.worktaskuser.entity.po.WorkTaskUserPO;
import com.yaj.jaso.business.worktaskuser.entity.vo.WorkTaskDetail;
import com.yaj.jaso.business.worktaskuser.service.WorkTaskUserService;

/*
 * @Description: 
 * @date: 2019-09-19
 */
@Service
public class WorkTaskService extends ServiceImpl<WorkTaskMapper, WorkTaskPO> {

    @Resource
    WorkTaskMapper workTaskMapper;
    @Autowired
    WorkTaskUserService wus;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    WorkTaskUserService taskUserService;
    @Autowired
    MeasureProblemService problemService;
    @Autowired
    QualityCheckService qualityService;
    @Autowired
    SecurityCheckService securityService;
    @Autowired
    MessageService messageService;
    @Autowired
    ReplyService replyService;
	public Object add(WorkTaskAdd po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		po.setJasoUserId(userInCache.getJasoUserId());
		po.setStatus(1);//待接受
		WorkTaskPO newpo = new WorkTaskPO();
		BeanUtil.copy(po,newpo);
		newpo.setCreateTime(null);
		if(insertOrUpdate(newpo)){
			if(!po.getWorkTaskUserList().isEmpty()){
				for(int i=0;i<po.getWorkTaskUserList().size();i++){
					po.getWorkTaskUserList().get(i).setWorkTaskId(newpo.getWorkTaskId());
					po.getWorkTaskUserList().get(i).setProjectId(po.getProjectId());
					po.getWorkTaskUserList().get(i).setCompanyId(po.getCompanyId());
					po.getWorkTaskUserList().get(i).setType(1);//执行人
				}
				if(wus.insertOrUpdateBatch(po.getWorkTaskUserList())){
					/////////////////////推送指定人员
					List<MessagePO> messageList = new ArrayList<>();
					List<String> userIds = new ArrayList<>();
					for(int i=0;i<po.getWorkTaskUserList().size();i++){
						if(po.getWorkTaskUserList().get(i).getType()==1){
							MessagePO message = new MessagePO();
							message.setAboutId(newpo.getWorkTaskId());
							message.setTitle(userInCache.getUserRealName()+"指定了一个工作任务单待给你！");
							message.setState(0);
							message.setCreateUserId(userInCache.getJasoUserId());
							message.setContent(newpo.getTaskTopic());
							message.setType(4);
							message.setJasoUserId(po.getWorkTaskUserList().get(i).getJasoUserId());
							message.setCompanyId(newpo.getCompanyId());
							messageList.add(message);
							userIds.add(po.getWorkTaskUserList().get(i).getJasoUserId().toString());
						}
					}
					if(!messageList.isEmpty()){
						if(messageService.insertBatch(messageList)){
							PushModel push = new PushModel();
							PushExample pushExample = new PushExample();
					    	push.setContent(userInCache.getUserRealName()+"指定了一个工作任务单待给你！");
					    	push.setTitle("工作任务单状态推送");
					    	pushExample.testSendPushWithCustomConfig(push,userIds);
						}
					}
				}
			}
		}else{
			return false;
		}
		return true;
	}
	public Object deleteBatchByIds(List<WorkTaskPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}
	//我分派的获取
	public Object selectMyOwnList(WorkTaskFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		//1、待接受 2、进行时 3、待验收 4、已完成
		List<Integer> statuListNew = new ArrayList<Integer>();
		if(!po.getStatusList().isEmpty()) {
			//1、待指派 2、待接受 3、进行时 4、待验收 5、已完成
			for(int i=0;i<po.getStatusList().size();i++){
				switch(po.getStatusList().get(i)){
				case 2:
					 statuListNew.add(1);break;
		        case 3:
		        	statuListNew.add(2);break;
		        case 4:
		        	statuListNew.add(3);break;
		        case 5:
		        	statuListNew.add(4);break;
		        default:
		            System.out.println("default");break;
				}
			}
		}
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
				+ "${2}.projectName", new WorkTaskPO(),new JasoUserPO(),new ProjectPO());
		mul.where("${work_task}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq(po.getStatus()!=null, "status", po.getStatus())
			.eq(po.getState()!=null, "state", po.getState())
			.eq("jasoUserId", userInCache.getJasoUserId())
			.eq("companyId", userInCache.getCompanyId())
			.ge(po.getFinishedDateStart()!=null,"finishedDate", po.getFinishedDateStart())
			.le(po.getFinishedDateEnd()!=null,"finishedDate", po.getFinishedDateEnd())
			.ge(po.getBeignDateStart()!=null,"taskTime", po.getBeignDateStart())
			.le(po.getBeginDateEnd()!=null,"taskTime", po.getBeginDateEnd())
			.in(!statuListNew.isEmpty(), "status", statuListNew);
		Object object=serviceMain.mulSelect(mul);
		return object;
	}
	//我负责的获取
	public Object selectMyDutyList(WorkTaskFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Map<String,Object>> object = new ArrayList<Map<String,Object>>();
		Wrapper<WorkTaskUserPO> userWrapper = new EntityWrapper<>();
		userWrapper.eq(po.getProjectId()!=null, "project_id", po.getProjectId())
		.eq("company_id", userInCache.getCompanyId())
		.eq("type", 1)
		.eq("jaso_user_id", userInCache.getJasoUserId());
		List<WorkTaskUserPO> userList= new ArrayList<>();
		userList=taskUserService.selectList(userWrapper);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<userList.size();i++){
			ids.add(userList.get(i).getWorkTaskId());
		}
		//1、待接受 2、进行时 3、待验收 4、已完成
		List<Integer> statuListNew = new ArrayList<Integer>();
		if(!po.getStatusList().isEmpty()) {
			//1、待指派 2、待接受 3、进行时 4、待验收 5、已完成
			for(int i=0;i<po.getStatusList().size();i++){
				switch(po.getStatusList().get(i)){
				case 2:
					 statuListNew.add(1);break;
		        case 3:
		        	statuListNew.add(2);break;
		        case 4:
		        	statuListNew.add(3);break;
		        case 5:
		        	statuListNew.add(4);break;
		        default:
		            System.out.println("default");break;
				}
			}
		}
		if(!ids.isEmpty()){
			MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
					+ "${2}.projectName", new WorkTaskPO(),new JasoUserPO(),new ProjectPO());
			mul.where("${work_task}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.ge(po.getFinishedDateStart()!=null,"finishedDate", po.getFinishedDateStart())
				.le(po.getFinishedDateEnd()!=null,"finishedDate", po.getFinishedDateEnd())
				.ge(po.getBeignDateStart()!=null,"taskTime", po.getBeignDateStart())
				.le(po.getBeginDateEnd()!=null,"taskTime", po.getBeginDateEnd())
				.in("workTaskId", ids)
				.in(!statuListNew.isEmpty(), "status", statuListNew);
			return serviceMain.mulSelect(mul);
		}
		return object;
	}
	//我参与的获取
	public Object selectMyJoinList(WorkTaskFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<WorkTaskUserPO> userWrapper = new EntityWrapper<>();
		List<Map<String,Object>> object = new ArrayList<Map<String,Object>>();
		userWrapper.eq(po.getProjectId()!=null, "project_id", po.getProjectId())
		.eq("company_id", userInCache.getCompanyId())
		.eq("jaso_user_id", userInCache.getJasoUserId());
		List<WorkTaskUserPO> userList= new ArrayList<>();
		userList=taskUserService.selectList(userWrapper);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<userList.size();i++){
			ids.add(userList.get(i).getWorkTaskId());
		}
		//1、待接受 2、进行时 3、待验收 4、已完成
		List<Integer> statuListNew = new ArrayList<Integer>();
		if(!po.getStatusList().isEmpty()) {
			//1、待指派 2、待接受 3、进行时 4、待验收 5、已完成
			for(int i=0;i<po.getStatusList().size();i++){
				switch(po.getStatusList().get(i)){
				case 2:
					 statuListNew.add(1);break;
		        case 3:
		        	statuListNew.add(2);break;
		        case 4:
		        	statuListNew.add(3);break;
		        case 5:
		        	statuListNew.add(4);break;
		        default:
		            System.out.println("default");break;
				}
			}
		}
		if(!ids.isEmpty()){
			MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
					+ "${2}.projectName", new WorkTaskPO(),new JasoUserPO(),new ProjectPO());
			mul.where("${work_task}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.ge(po.getFinishedDateStart()!=null,"finishedDate", po.getFinishedDateStart())
				.le(po.getFinishedDateEnd()!=null,"finishedDate", po.getFinishedDateEnd())
				.ge(po.getBeignDateStart()!=null,"taskTime", po.getBeignDateStart())
				.le(po.getBeginDateEnd()!=null,"taskTime", po.getBeginDateEnd())
				.in("workTaskId", ids)
				.in(!statuListNew.isEmpty(), "status", statuListNew);
			return serviceMain.mulSelect(mul);
		}
		return object;
	}
	//通过id获取参与人列表和消息目录
	public WorkTaskResult selectUserAndReply(WorkTaskPO po){
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		WorkTaskResult result = new WorkTaskResult();
		if(po.getWorkTaskId()!=null){
			MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
					+ "${1}.userIcon", new WorkTaskUserPO(),new JasoUserPO());
			mul.where("${work_task_user}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("workTaskId", po.getWorkTaskId())
				.eq("companyId", userInCache.getCompanyId());
			result.setUserList(serviceMain.mulSelect(mul)); 
			MulSelect mul2 = MulSelect.newInstance("${1}.userRealName,"
					+ "${1}.userIcon", new ReplyPO(),new JasoUserPO());
			mul2.where("${reply}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("aboutId", po.getWorkTaskId())
				.eq("type", 4)
				.eq("companyId", userInCache.getCompanyId());
			result.setReplyList(serviceMain.mulSelect(mul2)); 
		}else{
			return null;
		}
		return result;
	}
	//获取全部(实测实量、质量、安全、工作任务)
	public WorkAllResult getAllMyList(WorkAllOptions options){
		WorkAllResult result = new WorkAllResult();
		List<Map<String,Object>> allList = new ArrayList<>();
		if(options.getType()==1){//负责的所有
			//1、实测实量 2、质量管理 3、安全管理 、4、工作任务
        	//获取我负责的所有实测实量
        	MeasureProblemFind findMeasure = new MeasureProblemFind();
        	BeanUtil.copy(options,findMeasure);
        	List<Map<String,Object>> list1 = (List<Map<String,Object>>)problemService.selectMyDutyList(findMeasure);
        	if(!list1.isEmpty()){
        		List<Long> aboutId1 = new ArrayList<>();
        		for(int i=0;i<list1.size();i++){
        			aboutId1.add(Long.valueOf(list1.get(i).get("measureProblemId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new MeasureProblemUserPO(),new JasoUserPO());
    			mul.where("${measure_problem_user}")
    				.in("aboutId", aboutId1);
    			List<Map<String,Object>> users1=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list1.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users1.size();k++){
            			if(users1.get(k).get("aboutId").toString().equals(list1.get(j).get("measureProblemId").toString())){
            				aboutUserList.add(users1.get(k));
            			}
            		}
            		list1.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
        	insertKeyValue(list1,"实测实量");
        	//获取我负责的所有质量管理
        	QualityCheckFind findQuality = new QualityCheckFind();
			BeanUtil.copy(options,findQuality);
			List<Map<String,Object>> list2 = (List<Map<String,Object>>)qualityService.selectMyDutyList(findQuality);
			if(!list2.isEmpty()){
        		List<Long> aboutId2 = new ArrayList<>();
        		for(int i=0;i<list2.size();i++){
        			aboutId2.add(Long.valueOf(list2.get(i).get("qualityCheckId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new QualityCheckUsersPO(),new JasoUserPO());
    			mul.where("${quality_check_users}")
    				.eq("type", 1)
    				.in("aboutId", aboutId2);
    			List<Map<String,Object>> users2=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list2.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users2.size();k++){
            			if(users2.get(k).get("aboutId").toString().equals(list2.get(j).get("qualityCheckId").toString())){
            				aboutUserList.add(users2.get(k));
            			}
            		}
            		list2.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list2,"质量管理");
			//获取我负责的所有安全管理
			SecurityCheckFind findSecurity = new SecurityCheckFind();
			BeanUtil.copy(options,findSecurity);
			List<Map<String,Object>> list3 = (List<Map<String,Object>>)securityService.selectMyDutyList(findSecurity);
			if(!list3.isEmpty()){
        		List<Long> aboutId3 = new ArrayList<>();
        		for(int i=0;i<list3.size();i++){
        			aboutId3.add(Long.valueOf(list3.get(i).get("securityCheckId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new QualityCheckUsersPO(),new JasoUserPO());
    			mul.where("${quality_check_users}")
    				.eq("type", 2)
    				.in("aboutId", aboutId3);
    			List<Map<String,Object>> users3=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list3.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users3.size();k++){
            			if(users3.get(k).get("aboutId").toString().equals(list3.get(j).get("securityCheckId").toString())){
            				aboutUserList.add(users3.get(k));
            			}
            		}
            		list3.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list3,"安全管理");
			//获取我负责的所有工作任务
			WorkTaskFind findTask = new WorkTaskFind();
        	BeanUtil.copy(options,findTask);
			List<Map<String,Object>> list4=(List<Map<String,Object>>)selectMyDutyList(findTask);
			if(!list4.isEmpty()){
        		List<Long> aboutId4 = new ArrayList<>();
        		for(int i=0;i<list4.size();i++){
        			aboutId4.add(Long.valueOf(list4.get(i).get("workTaskId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new WorkTaskUserPO(),new JasoUserPO());
    			mul.where("${work_task_user}")
    				.in("workTaskId", aboutId4);
    			List<Map<String,Object>> users4=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list4.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users4.size();k++){
            			if(users4.get(k).get("workTaskId").toString().equals(list4.get(j).get("workTaskId").toString())){
            				aboutUserList.add(users4.get(k));
            			}
            		}
            		list4.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list4,"工作任务");
			allList.addAll(list1);
			allList.addAll(list2);
			allList.addAll(list3);
			allList.addAll(list4);
		}
		if(options.getType()==2){//分派的所有
			//1、实测实量 2、质量管理 3、安全管理 、4、工作任务
        	//获取我负责的所有实测实量
        	MeasureProblemFind findMeasure = new MeasureProblemFind();
        	BeanUtil.copy(options,findMeasure);
        	List<Map<String,Object>> list1 = (List<Map<String,Object>>)problemService.selectMyOwnList(findMeasure);
        	if(!list1.isEmpty()){
        		List<Long> aboutId1 = new ArrayList<>();
        		for(int i=0;i<list1.size();i++){
        			aboutId1.add(Long.valueOf(list1.get(i).get("measureProblemId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new MeasureProblemUserPO(),new JasoUserPO());
    			mul.where("${measure_problem_user}")
    				.in("aboutId", aboutId1);
    			List<Map<String,Object>> users1=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list1.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users1.size();k++){
            			if(users1.get(k).get("aboutId").toString().equals(list1.get(j).get("measureProblemId").toString())){
            				aboutUserList.add(users1.get(k));
            			}
            		}
            		list1.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
        	insertKeyValue(list1,"实测实量");
        	//获取我负责的所有质量管理
        	QualityCheckFind findQuality = new QualityCheckFind();
			BeanUtil.copy(options,findQuality);
			List<Map<String,Object>> list2 = (List<Map<String,Object>>)qualityService.selectMyOwnList(findQuality);
			if(!list2.isEmpty()){
        		List<Long> aboutId2 = new ArrayList<>();
        		for(int i=0;i<list2.size();i++){
        			aboutId2.add(Long.valueOf(list2.get(i).get("qualityCheckId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new QualityCheckUsersPO(),new JasoUserPO());
    			mul.where("${quality_check_users}")
    				.eq("type", 1)
    				.in("aboutId", aboutId2);
    			List<Map<String,Object>> users2=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list2.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users2.size();k++){
            			if(users2.get(k).get("aboutId").toString().equals(list2.get(j).get("qualityCheckId").toString())){
            				aboutUserList.add(users2.get(k));
            			}
            		}
            		list2.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list2,"质量管理");
			//获取我负责的所有安全管理
			SecurityCheckFind findSecurity = new SecurityCheckFind();
			BeanUtil.copy(options,findSecurity);
			List<Map<String,Object>> list3 = (List<Map<String,Object>>)securityService.selectMyOwnList(findSecurity);
			if(!list3.isEmpty()){
        		List<Long> aboutId3 = new ArrayList<>();
        		for(int i=0;i<list3.size();i++){
        			aboutId3.add(Long.valueOf(list3.get(i).get("securityCheckId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new QualityCheckUsersPO(),new JasoUserPO());
    			mul.where("${quality_check_users}")
    				.eq("type", 2)
    				.in("aboutId", aboutId3);
    			List<Map<String,Object>> users3=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list3.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users3.size();k++){
            			if(users3.get(k).get("aboutId").toString().equals(list3.get(j).get("securityCheckId").toString())){
            				aboutUserList.add(users3.get(k));
            			}
            		}
            		list3.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list3,"安全管理");
			//获取我负责的所有工作任务
			WorkTaskFind findTask = new WorkTaskFind();
        	BeanUtil.copy(options,findTask);
			List<Map<String,Object>> list4=(List<Map<String,Object>>)selectMyOwnList(findTask);
			if(!list4.isEmpty()){
        		List<Long> aboutId4 = new ArrayList<>();
        		for(int i=0;i<list4.size();i++){
        			aboutId4.add(Long.valueOf(list4.get(i).get("workTaskId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new WorkTaskUserPO(),new JasoUserPO());
    			mul.where("${work_task_user}")
    				.in("workTaskId", aboutId4);
    			List<Map<String,Object>> users4=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list4.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users4.size();k++){
            			if(users4.get(k).get("workTaskId").toString().equals(list4.get(j).get("workTaskId").toString())){
            				aboutUserList.add(users4.get(k));
            			}
            		}
            		list4.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list4,"工作任务");
			allList.addAll(list1);
			allList.addAll(list2);
			allList.addAll(list3);
			allList.addAll(list4);
		}
		if(options.getType()==3){//参与的所有
			//1、实测实量 2、质量管理 3、安全管理 、4、工作任务
        	//获取我负责的所有实测实量
        	MeasureProblemFind findMeasure = new MeasureProblemFind();
        	BeanUtil.copy(options,findMeasure);
        	List<Map<String,Object>> list1 = (List<Map<String,Object>>)problemService.selectMyJoinList(findMeasure);
        	if(!list1.isEmpty()){
        		List<Long> aboutId1 = new ArrayList<>();
        		for(int i=0;i<list1.size();i++){
        			aboutId1.add(Long.valueOf(list1.get(i).get("measureProblemId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new MeasureProblemUserPO(),new JasoUserPO());
    			mul.where("${measure_problem_user}")
    				.in("aboutId", aboutId1);
    			List<Map<String,Object>> users1=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list1.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users1.size();k++){
            			if(users1.get(k).get("aboutId").toString().equals(list1.get(j).get("measureProblemId").toString())){
            				aboutUserList.add(users1.get(k));
            			}
            		}
            		list1.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
        	insertKeyValue(list1,"实测实量");
        	//获取我负责的所有质量管理
        	QualityCheckFind findQuality = new QualityCheckFind();
			BeanUtil.copy(options,findQuality);
			List<Map<String,Object>> list2 = (List<Map<String,Object>>)qualityService.selectMyJoinList(findQuality);
			if(!list2.isEmpty()){
        		List<Long> aboutId2 = new ArrayList<>();
        		for(int i=0;i<list2.size();i++){
        			aboutId2.add(Long.valueOf(list2.get(i).get("qualityCheckId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new QualityCheckUsersPO(),new JasoUserPO());
    			mul.where("${quality_check_users}")
    				.eq("type", 1)
    				.in("aboutId", aboutId2);
    			List<Map<String,Object>> users2=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list2.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users2.size();k++){
            			if(users2.get(k).get("aboutId").toString().equals(list2.get(j).get("qualityCheckId").toString())){
            				aboutUserList.add(users2.get(k));
            			}
            		}
            		list2.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list2,"质量管理");
			//获取我负责的所有安全管理
			SecurityCheckFind findSecurity = new SecurityCheckFind();
			BeanUtil.copy(options,findSecurity);
			List<Map<String,Object>> list3 = (List<Map<String,Object>>)securityService.selectMyJoinList(findSecurity);
			if(!list3.isEmpty()){
        		List<Long> aboutId3 = new ArrayList<>();
        		for(int i=0;i<list3.size();i++){
        			aboutId3.add(Long.valueOf(list3.get(i).get("securityCheckId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new QualityCheckUsersPO(),new JasoUserPO());
    			mul.where("${quality_check_users}")
    				.eq("type", 2)
    				.in("aboutId", aboutId3);
    			List<Map<String,Object>> users3=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list3.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users3.size();k++){
            			if(users3.get(k).get("aboutId").toString().equals(list3.get(j).get("securityCheckId").toString())){
            				aboutUserList.add(users3.get(k));
            			}
            		}
            		list3.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list3,"安全管理");
			//获取我负责的所有工作任务
			WorkTaskFind findTask = new WorkTaskFind();
        	BeanUtil.copy(options,findTask);
			List<Map<String,Object>> list4=(List<Map<String,Object>>)selectMyJoinList(findTask);
			if(!list4.isEmpty()){
        		List<Long> aboutId4 = new ArrayList<>();
        		for(int i=0;i<list4.size();i++){
        			aboutId4.add(Long.valueOf(list4.get(i).get("workTaskId").toString()));
            	}
            	MulSelect mul = MulSelect.newInstance("${1}.userRealName", new WorkTaskUserPO(),new JasoUserPO());
    			mul.where("${work_task_user}")
    				.in("workTaskId", aboutId4);
    			List<Map<String,Object>> users4=(List<Map<String,Object>>)serviceMain.mulSelect(mul);
            	for(int j=0;j<list4.size();j++){
            		List<Map<String,Object>> aboutUserList = new ArrayList<>();
            		for(int k=0;k<users4.size();k++){
            			if(users4.get(k).get("workTaskId").toString().equals(list4.get(j).get("workTaskId").toString())){
            				aboutUserList.add(users4.get(k));
            			}
            		}
            		list4.get(j).put("aboutUserList", aboutUserList);
            	}
        	}
			insertKeyValue(list4,"工作任务");
			allList.addAll(list1);
			allList.addAll(list2);
			allList.addAll(list3);
			allList.addAll(list4);
		}
		Collections.sort(allList, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
            	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
            	String test=o1.get("createTime").toString();
            	String test2=o2.get("createTime").toString();
            	
            	/*String scurrtest1 = o2.get("createTime");
                Long map1value =Long.valueOf(scurrtest) ;
                Long map2value = Long.valueOf(scurrtest1);
                Long result = (map1value - map2value);*/
                try {
					return format1.parse(test2).compareTo(format1.parse(test));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                return 0;
            }
        });
		result.setDataList(allList);
		return result;
	}
	public List<Map<String,Object>> insertKeyValue(List<Map<String,Object>> list,String name){
		for(int i=0;i<list.size();i++){
			list.get(i).put("partFlag", name);
		}
		return list;
	}
	public Object getDetail(WorkTaskPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache= (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		WorkTaskDetail detail = new WorkTaskDetail();
		if(po.getWorkTaskId()!=null){
			MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
					+ "${2}.projectName", new WorkTaskPO(),new JasoUserPO(),new ProjectPO());
			mul.where("${work_task}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.eq("workTaskId", po.getWorkTaskId());
			List<Map> object=(List<Map>)serviceMain.mulSelect(mul);
			detail.setWorkTask(object.get(0));
			MulSelect mul2 = MulSelect.newInstance("${1}.userRealName,"
					+ "${1}.userIcon", new WorkTaskUserPO(),new JasoUserPO());
			mul2.where("${work_task_user}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.eq("workTaskId", po.getWorkTaskId());
			Object object2=serviceMain.mulSelect(mul2);
			detail.setUserInfo(object2);
		}
		return detail;
	}
	public Object check(WorkTaskCheck po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getWorkTaskId()!=null){
			WorkTaskPO task = selectById(po.getWorkTaskId());
			if(task!=null){
				if(po.getType()!=null){
					if(po.getType()==1){
						task.setStatus(4);
					}
					if(po.getType()==2){
						task.setStatus(2);
					}
				}
				if(insertOrUpdate(task)){
					ReplyPO reply = new ReplyPO();
					reply.setAboutId(po.getWorkTaskId());
					reply.setProjectId(task.getProjectId());
					if(po.getType()==1){
						reply.setColorState(2);
						reply.setReplyContent("验收通过！");
					}else if(po.getType()==2){
						reply.setColorState(3);
						reply.setReplyContent("验收不通过！");
					}
					reply.setJasoUserId(cacheUser.getJasoUserId());
					reply.setReplyType(4);
					reply.setCompanyId(cacheUser.getCompanyId());
					if(replyService.insertOrUpdate(reply)){
						Wrapper<WorkTaskUserPO> qualityUsers = new EntityWrapper<>();
						qualityUsers.eq("type", 1)
						.eq("work_task_id", task.getWorkTaskId());
						List<WorkTaskUserPO> userList = new ArrayList<>();
						userList=taskUserService.selectList(qualityUsers);
						if(!userList.isEmpty()){
							List<String> userIds = new ArrayList<>();
							List<MessagePO>	messageList = new ArrayList<>();
							for(int i=0;i<userList.size();i++){
								MessagePO message = new MessagePO();
								message.setAboutId(task.getWorkTaskId());
								message.setCompanyId(task.getCompanyId());
								message.setState(0);
								message.setCreateUserId(cacheUser.getJasoUserId());
								message.setContent(task.getTaskTopic());
								message.setJasoUserId(userList.get(i).getJasoUserId());
								message.setType(4);
								message.setTitle("你指定的工作任务单已被验收！");
								messageList.add(message);
								userIds.add(userList.get(i).getJasoUserId().toString());
							}
							if(!messageList.isEmpty()){
								if(messageService.insertBatch(messageList)){
									PushModel push = new PushModel();
									PushExample pushExample = new PushExample();
							    	push.setContent("你指定的工作任务单已被验收！");
							    	push.setTitle("工作任务单状态推送");
							    	pushExample.testSendPushWithCustomConfig(push,userIds);
								}
							}
						}
					}
				}else{
					return false;
				}
			}
		}
		
		return true;
	}
	public Object setProcess(WorkTaskPO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getWorkTaskId()!=null){
			WorkTaskPO old = new WorkTaskPO();
			old = selectById(po.getWorkTaskId());
			old.setProcess(po.getProcess());
			if(po.getProcess()==100){
				old.setStatus(3);
			}
			if(insertOrUpdate(old)){
				ReplyPO reply = new ReplyPO();
				reply.setAboutId(old.getWorkTaskId());
				reply.setCompanyId(cacheUser.getCompanyId());
				reply.setProjectId(old.getProjectId());
				reply.setJasoUserId(cacheUser.getJasoUserId());
				reply.setColorState(2);
				reply.setReplyContent("整改进度更新："+po.getProcess()+"%");
				reply.setReplyType(4);
				if(replyService.insertOrUpdate(reply)){
					///////////整改进度推送创建人
					List<MessagePO> messageList = new ArrayList<>();
					List<String> userIds = new ArrayList<>();
					MessagePO message = new MessagePO();
					message.setTitle(cacheUser.getUserRealName()+"设置工作任务单进度为"+po.getProcess()+"%");
					message.setAboutId(old.getWorkTaskId());
					message.setState(0);
					message.setContent(old.getTaskTopic());
					message.setCreateUserId(cacheUser.getJasoUserId());
					message.setType(4);
					message.setJasoUserId(old.getJasoUserId());
					message.setCompanyId(old.getCompanyId());
					messageList.add(message);
					userIds.add(old.getJasoUserId().toString());
					if(!messageList.isEmpty()){
						if(messageService.insertBatch(messageList)){
							PushModel push = new PushModel();
							PushExample pushExample = new PushExample();
					    	push.setContent(cacheUser.getUserRealName()+"设置工作任务单进度为"+po.getProcess()+"%");
					    	push.setTitle("工作任务单状态推送");
					    	pushExample.testSendPushWithCustomConfig(push,userIds);
						}
					}
				}
			}else{
				return false;
			}
		}
		return true;
	}
	public Object acceptWorkTask(WorkTaskCheck po) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getWorkTaskId()!=null){
			WorkTaskPO old = new WorkTaskPO();
			old = selectById(po.getWorkTaskId());
			if(po.getIsAccept()==1){
				old.setStatus(2);
			}
			if(po.getIsAccept()==2){
				old.setStatus(4);
			}
			if(insertOrUpdate(old)){
				ReplyPO reply = new ReplyPO();
				reply.setAboutId(old.getWorkTaskId());
				reply.setCompanyId(cacheUser.getCompanyId());
				reply.setProjectId(old.getProjectId());
				reply.setJasoUserId(cacheUser.getJasoUserId());
				if(po.getIsAccept()==1){
					reply.setReplyContent(cacheUser.getUserRealName()+"接受了该任务");
					reply.setColorState(2);
				}
				if(po.getIsAccept()==2){
					reply.setReplyContent(cacheUser.getUserRealName()+"拒绝了该任务");
					reply.setColorState(3);
				}
				reply.setReplyType(4);
				if(replyService.insertOrUpdate(reply)){
					/////////////////////推送创建人员
					List<MessagePO> messageList = new ArrayList<>();
					List<String> userIds = new ArrayList<>();
					MessagePO message = new MessagePO();
					if(po.getIsAccept()==1){
						message.setTitle(cacheUser.getUserRealName()+"接受了你指派的一个工作任务单");
					}else{
						message.setTitle(cacheUser.getUserRealName()+"拒绝了你指派的一个工作任务单");
					}
					message.setCreateUserId(cacheUser.getJasoUserId());
					message.setAboutId(old.getWorkTaskId());
					message.setState(0);
					message.setType(4);
					message.setContent(old.getTaskTopic());
					message.setJasoUserId(old.getJasoUserId());
					message.setCompanyId(old.getCompanyId());
					messageList.add(message);
					userIds.add(old.getJasoUserId().toString());
					if(!messageList.isEmpty()){
						if(messageService.insertBatch(messageList)){
							PushModel push = new PushModel();
							PushExample pushExample = new PushExample();
					    	push.setContent(message.getTitle());
					    	push.setTitle("工作任务单状态推送");
					    	pushExample.testSendPushWithCustomConfig(push,userIds);
						}
					}
					return true;
				}
			}else{
				return false;
			}
		}
		return true;
	}
	public Object addAboutUserList(List<WorkTaskUserPO> po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		for(int i=0;i<po.size();i++){
			po.get(i).setCompanyId(userInCache.getCompanyId());
		}
		return taskUserService.insertBatch(po);
	}
	public Object getList(WorkTaskVo po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
				+ "${2}.projectName", new WorkTaskPO(),new JasoUserPO(),new ProjectPO());
		mul.setPage(po.getPageVo().getPageNo(), po.getPageVo().getPageSize());
		mul.where("${work_task}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", userInCache.getCompanyId())
			.eq(po.getStatus()!=null,"status", po.getStatus())
			.eq(po.getState()!=null,"", po.getState());
		return serviceMain.mulSelect(mul);
	}
	
}
