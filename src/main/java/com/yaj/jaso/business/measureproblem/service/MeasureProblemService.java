package com.yaj.jaso.business.measureproblem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
import com.yaj.jaso.business.measurepaper.entity.po.MeasurePaperPO;
import com.yaj.jaso.business.measurepaper.service.MeasurePaperService;
import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.measurepoint.service.MeasurePointService;
import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasurePointInfo;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemAdd;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemCheck;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemFind;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemResult;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemVo;
import com.yaj.jaso.business.measureproblem.mapper.MeasureProblemMapper;
import com.yaj.jaso.business.measureproblemuser.entity.po.MeasureProblemUserPO;
import com.yaj.jaso.business.measureproblemuser.entity.vo.MeasureProblemUserVo;
import com.yaj.jaso.business.measureproblemuser.service.MeasureProblemUserService;
import com.yaj.jaso.business.message.entity.po.MessagePO;
import com.yaj.jaso.business.message.service.MessageService;
import com.yaj.jaso.business.pointdatainputlog.entity.po.PointDataInputLogPO;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;
import com.yaj.jaso.business.reply.entity.po.ReplyPO;
import com.yaj.jaso.business.reply.service.ReplyService;

/*
 * @Description: 
 * @date: 2019-08-28
 */
@Service
public class MeasureProblemService extends ServiceImpl<MeasureProblemMapper, MeasureProblemPO> {

    @Resource
    MeasureProblemMapper measureProblemMapper;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    MeasureProblemUserService problemService;
    @Autowired
    ReplyService replyService;
    @Autowired
    MeasurePointService pointService;
    @Autowired
    MeasurePaperService paperService;
    @Autowired
    MessageService messageService;
	public Object add(List<MeasureProblemAdd> po) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<MeasureProblemUserPO> rectifyUsers = new ArrayList<>();
		List<MeasureProblemPO> problems = new ArrayList<>(); 
		for(int i=0;i<po.size();i++){
			MeasureProblemPO ppo = new MeasureProblemPO();
			po.get(i).setCompanyId(jasoUserPO.getCompanyId());
			if(po.get(i).getMeasureProblemId()!=null){
				po.get(i).setStatus(2);
			}
			BeanUtil.copy(po.get(i),ppo);
			problems.add(ppo);
		}
		if(insertOrUpdateBatch(problems)){
			for(int j=0;j<problems.size();j++){
				if(po.get(j).getRectifyUserIds()!=null){
					for(int i=0;i<po.get(j).getRectifyUserIds().size();i++){
						MeasureProblemUserPO rectifyUser = new MeasureProblemUserPO();
						rectifyUser.setAboutId(problems.get(j).getMeasureProblemId());
						rectifyUser.setCompanyId(jasoUserPO.getCompanyId());
						rectifyUser.setProjectId(problems.get(j).getProjectId());
						rectifyUser.setType(1);
						rectifyUser.setJasoUserId(po.get(j).getRectifyUserIds().get(i));
						rectifyUsers.add(rectifyUser);
					}
				}
			}
			if(problemService.insertBatch(rectifyUsers)){
				/////////////////////推送指定人员
				List<MessagePO> messageList = new ArrayList<>();
				List<String> userIds = new ArrayList<>();
				for(int i=0;i<rectifyUsers.size();i++){
					if(rectifyUsers.get(i).getType()==1){
						MessagePO message = new MessagePO();
						message.setAboutId(rectifyUsers.get(i).getAboutId());
						for(int g=0;g<problems.size();g++){
							if(problems.get(g).getMeasureProblemId().equals(rectifyUsers.get(i).getAboutId())){
								message.setContent(problems.get(g).getCheckSite());
							}
						}
						message.setCreateUserId(jasoUserPO.getJasoUserId());
						message.setTitle(jasoUserPO.getUserRealName()+"指派了一个爆点整改单待你整改！");
						message.setState(0);
						message.setType(3);
						message.setJasoUserId(rectifyUsers.get(i).getJasoUserId());
						message.setCompanyId(jasoUserPO.getCompanyId());
						messageList.add(message);
						userIds.add(rectifyUsers.get(i).getJasoUserId().toString());
					}
				}
				if(!messageList.isEmpty()){
					if(messageService.insertBatch(messageList)){
						PushModel push = new PushModel();
						PushExample pushExample = new PushExample();
				    	push.setContent(jasoUserPO.getUserRealName()+"指定了一个爆点整改单给你！");
				    	push.setTitle("爆点清单状态推送");
				    	pushExample.testSendPushWithCustomConfig(push,userIds);
					}
				}
			}
		}else{
			return false;
		}
		return true;
	}

	public Object deleteBatchByIds(List<MeasureProblemPO> po) {
		// TODO Auto-generated method stub
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(MeasureProblemVo po) {
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${2}.checkName,${3}.x,${3}.y", new MeasureProblemPO(), new JasoUserPO(),new ProjectCheckTypePO(),new MeasurePointPO());
		mul.setPage(po.getPageVo().getPageNo(), po.getPageVo().getPageSize());
		mul.where("${measure_problem}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq(po.getStatus()!=null,"status", po.getStatus())
			.eq("companyId", jasoUserPO.getCompanyId())
			.eq("jasoUserId", jasoUserPO.getJasoUserId());
		mul.setOrderBy("measureproblem.create_time desc");	
		return serviceMain.mulSelect(mul);
	}

	public Object selectList(MeasureProblemPO po) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<MeasureProblemResult> results = new ArrayList<>();
		List<MeasureProblemResult> resultList = new ArrayList<>();
		results=measureProblemMapper.selectLists(po.getCompanyId(), po.getProjectId(), jasoUserPO.getJasoUserId(), po.getStatus());
		for(int i=0;i<results.size();i++){
			if(resultList.isEmpty()){
				results.get(i).setInputDatas(results.get(i).getInputData().toString());
				resultList.add(results.get(i));
			}else{
				boolean flag = true;
				for(int j=0;j<resultList.size();j++){
					if(results.get(i).getMeasurePointId().equals(resultList.get(j).getMeasurePointId()) 
							&& results.get(i).getProjectCheckTypeId().equals(resultList.get(j).getProjectCheckTypeId())){
						flag=false;
						
					}
				}
				if(flag){
					results.get(i).setInputDatas(results.get(i).getInputData().toString());
					resultList.add(results.get(i));
				}
			}
		}
		for(int x=0;x<resultList.size();x++){
			for(int y=0;y<results.size();y++){
				if(!resultList.get(x).getMeasureProblemId().equals(results.get(y).getMeasureProblemId())){
					if(resultList.get(x).getMeasurePointId().equals(results.get(y).getMeasurePointId()) 
							&& resultList.get(x).getProjectCheckTypeId().equals(results.get(y).getProjectCheckTypeId()) && results.get(y).getInputData()!=null){
						if(resultList.get(x).getInputDatas()==null){
							resultList.get(x).setInputDatas(results.get(y).getInputData().toString());
						}else{
							resultList.get(x).setInputDatas(resultList.get(x).getInputDatas()+","+results.get(y).getInputData());
						}
						
					}
				}
				
			}
		}
		return resultList;
	}
 
	public Object selectAboutUserList(MeasureProblemPO po) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${1}.userIcon", new MeasureProblemUserPO(), new JasoUserPO());
		mul.where("${measure_problem_user}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq(po.getMeasureProblemId()!=null,"aboutId",po.getMeasureProblemId())
			.eq("companyId", jasoUserPO.getCompanyId());
		return serviceMain.mulSelect(mul);
	}

	public Object addAboutUser(MeasureProblemUserVo po) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(!po.getJasoUserIds().isEmpty()){
			List<MeasureProblemUserPO> adds = new ArrayList<>();
			for(int i=0;i<po.getJasoUserIds().size();i++){
				MeasureProblemUserPO userPO = new MeasureProblemUserPO();
				userPO.setAboutId(po.getAboutId());
				userPO.setType(2);
				userPO.setCompanyId(jasoUserPO.getJasoUserId());
				userPO.setProjectId(po.getProjectId());
				userPO.setJasoUserId(po.getJasoUserIds().get(i));
				adds.add(userPO);
			}
			problemService.insertBatch(adds);
		}
		return true;
	}

	public Object checkMeasureProblem(MeasureProblemCheck po) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getMeasureProblemId()!=null){
			MeasureProblemPO mpo = new MeasureProblemPO();
			mpo = selectById(po.getMeasureProblemId());
			String message="验收";
			Integer colorState=2;
			if(po.getState()==2){
				mpo.setStatus(2);
				colorState=3;
				message=message+"不通过";
			}
			if(po.getState()==1){
				message=message+"通过";
				mpo.setStatus(4);
			}
			if(!insertOrUpdate(mpo)){
				return false;
			}else{
				ReplyPO reply = new ReplyPO();
				reply.setAboutId(po.getMeasureProblemId());
				reply.setCompanyId(jasoUserPO.getCompanyId());
				reply.setProjectId(po.getProjectId());
				reply.setJasoUserId(jasoUserPO.getJasoUserId());
				reply.setColorState(colorState);
				reply.setReplyContent(message);
				reply.setReplyType(1);
				if(replyService.insertOrUpdate(reply)){
					Wrapper<MeasureProblemUserPO> qualityUsers = new EntityWrapper<>();
					qualityUsers.eq("type", 1)
					.eq("about_id", mpo.getMeasureProblemId());
					List<MeasureProblemUserPO> userList = new ArrayList<>();
					userList=problemService.selectList(qualityUsers);
					if(!userList.isEmpty()){
						List<String> userIds = new ArrayList<>();
						List<MessagePO>	messageList = new ArrayList<>();
						for(int i=0;i<userList.size();i++){
							MessagePO messages = new MessagePO();
							messages.setAboutId(mpo.getMeasureProblemId());
							messages.setCompanyId(mpo.getCompanyId());
							messages.setContent(mpo.getCheckSite());
							messages.setState(0);
							messages.setCreateUserId(jasoUserPO.getJasoUserId());
							messages.setJasoUserId(userList.get(i).getJasoUserId());
							messages.setType(3);
							messages.setTitle("你指定的爆点清单已被验收！");
							messageList.add(messages);
							userIds.add(userList.get(i).getJasoUserId().toString());
						}
						if(!messageList.isEmpty()){
							if(messageService.insertBatch(messageList)){
								PushModel push = new PushModel();
								PushExample pushExample = new PushExample();
						    	push.setContent("你指定的爆点清单已被验收！");
						    	push.setTitle("爆点清单状态推送");
						    	pushExample.testSendPushWithCustomConfig(push,userIds);
							}
						}
					}
				}
			}
		}
		return true;
	}

	public Object setMeasureProblemCheckProgress(MeasureProblemPO po) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getProcess()!=null){
			po.setProcess(po.getProcess());
			if(po.getProcess()==100){
				po.setStatus(3);
			}
			if(insertOrUpdate(po)){
				ReplyPO reply = new ReplyPO();
				reply.setAboutId(po.getMeasureProblemId());
				reply.setCompanyId(jasoUserPO.getCompanyId());
				reply.setProjectId(po.getProjectId());
				reply.setColorState(2);
				reply.setJasoUserId(jasoUserPO.getJasoUserId());
				reply.setReplyContent("整改进度更新："+po.getProcess()+"%");
				reply.setReplyType(1);
				if(replyService.insertOrUpdate(reply)){
					///////////整改进度推送创建人
					List<MessagePO> messageList = new ArrayList<>();
					List<String> userIds = new ArrayList<>();
					MessagePO message = new MessagePO();
					message.setTitle(jasoUserPO.getUserRealName()+"设置爆点整改单进度为"+po.getProcess()+"%");
					message.setContent(po.getCheckSite());
					message.setAboutId(po.getMeasureProblemId());
					message.setState(0);
					message.setCreateUserId(jasoUserPO.getJasoUserId());
					message.setType(3);
					message.setJasoUserId(po.getJasoUserId());
					message.setCompanyId(po.getCompanyId());
					messageList.add(message);
					userIds.add(po.getJasoUserId().toString());
					if(!messageList.isEmpty()){
						if(messageService.insertBatch(messageList)){
							PushModel push = new PushModel();
							PushExample pushExample = new PushExample();
					    	push.setContent(jasoUserPO.getUserRealName()+"设置爆点整改单进度为"+po.getProcess()+"%");
					    	push.setTitle("爆点清单状态推送");
					    	pushExample.testSendPushWithCustomConfig(push,userIds);
						}
					}
				}
			}
		}
		return true;
	}

	public Object selectByIds(MeasureProblemPO po) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUserPO = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MeasureProblemResult results = new MeasureProblemResult();
		MeasurePointInfo result = new MeasurePointInfo();
		results=measureProblemMapper.selectDetail(jasoUserPO.getCompanyId(), po.getProjectId(), po.getMeasureProblemId());
		if(results!=null){
			results.setInputDatas(results.getInputData().toString());
			Wrapper<MeasureProblemPO> wrapper = new EntityWrapper<>();
			wrapper.eq("measure_site_id", results.getMeasureSiteId())
			.eq("project_check_type_id", results.getProjectCheckTypeId())
			.eq("measure_point_id", results.getMeasurePointId());
			List<MeasureProblemPO> problems = new ArrayList<>();
			problems=selectList(wrapper);
			if(!problems.isEmpty()){
				for(MeasureProblemPO e:problems){
					if(!e.getMeasureProblemId().equals(results.getMeasureProblemId())){
						results.setInputDatas(results.getInputDatas()+","+e.getInputData());
					}
				}
			}
		}
		if(results!=null){
			MeasureProblemPO newpo = new MeasureProblemPO();
			newpo=selectById(results.getMeasureProblemId());
			if(newpo!=null){
				MeasurePointPO point=pointService.selectById(newpo.getMeasurePointId());
				if(point!=null){
					result.setX(point.getX());
					result.setY(point.getY());
					MeasurePaperPO paper= paperService.selectById(point.getMeasurePaperId());
					result.setPaperUrl(paper.getPaperUrl());
				}
			}
			result.setMeasureProblem(results);
			result.setUserInfo(selectAboutUserList(po));
			result.setMeasureProblem(results);
		}
		return result;
	}
	
	//我负责的获取
	public Object selectMyDutyList(MeasureProblemFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Map<String,Object>> object = new ArrayList<Map<String,Object>>();
		Wrapper<MeasureProblemUserPO> userWrapper = new EntityWrapper<>();
		userWrapper.eq(po.getProjectId()!=null, "project_id", po.getProjectId())
		.eq("company_id", userInCache.getCompanyId())
		.eq("type", 1)
		.eq("jaso_user_id", userInCache.getJasoUserId());
		List<MeasureProblemUserPO> userList= new ArrayList<>();
		userList=problemService.selectList(userWrapper);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<userList.size();i++){
			ids.add(userList.get(i).getAboutId());
		}
		//1、待指派 2、进行时 3、待验收 4、已完成
		List<Integer> statuListNew = new ArrayList<Integer>();
		if(!po.getStatusList().isEmpty()) {
			//1、待指派 2、待接受 3、进行时 4、待验收 5、已完成
			for(int i=0;i<po.getStatusList().size();i++){
				switch(po.getStatusList().get(i)){
				case 1:
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
					+ "${2}.projectName,${3}.checkName", new MeasureProblemPO(),new JasoUserPO(),new ProjectPO(),new ProjectCheckTypePO());
			mul.where("${measure_problem}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.ge(po.getFinishedDateStart()!=null,"finishedDate", po.getFinishedDateStart())
				.le(po.getFinishedDateEnd()!=null,"finishedDate", po.getFinishedDateEnd())
				.ge(po.getBeignDateStart()!=null,"checkDate", po.getBeignDateStart())
				.le(po.getBeginDateEnd()!=null,"checkDate", po.getBeginDateEnd())
				.in("measureProblemId", ids)
				.in(!statuListNew.isEmpty(), "status", statuListNew);
			return serviceMain.mulSelect(mul);
		}
		return object;
	}
	//我分派的
	public Object selectMyOwnList(MeasureProblemFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		//1、待指派 2、进行时 3、待验收 4、已完成
		List<Integer> statuListNew = new ArrayList<Integer>();
		if(!po.getStatusList().isEmpty()) {
			//1、待指派 2、待接受 3、进行时 4、待验收 5、已完成
			for(int i=0;i<po.getStatusList().size();i++){
				switch(po.getStatusList().get(i)){
				case 1:
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
				+ "${2}.projectName,${3}.checkName", new MeasureProblemPO(),new JasoUserPO(),new ProjectPO(),new ProjectCheckTypePO());
		mul.where("${measure_problem}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", userInCache.getCompanyId())
			.ge(po.getFinishedDateStart()!=null,"finishedDate", po.getFinishedDateStart())
			.le(po.getFinishedDateEnd()!=null,"finishedDate", po.getFinishedDateEnd())
			.ge(po.getBeignDateStart()!=null,"checkDate", po.getBeignDateStart())
			.le(po.getBeginDateEnd()!=null,"checkDate", po.getBeginDateEnd())
			.eq("jasoUserId", userInCache.getJasoUserId())
			.in(!statuListNew.isEmpty(), "status", statuListNew);
		return serviceMain.mulSelect(mul);
	}
	//我参与的
	public Object selectMyJoinList(MeasureProblemFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Map<String,Object>> object = new ArrayList<Map<String,Object>>();
		Wrapper<MeasureProblemUserPO> userWrapper = new EntityWrapper<>();
		userWrapper.eq(po.getProjectId()!=null, "project_id", po.getProjectId())
		.eq("company_id", userInCache.getCompanyId())
		.eq("jaso_user_id", userInCache.getJasoUserId());
		List<MeasureProblemUserPO> userList= new ArrayList<>();
		userList=problemService.selectList(userWrapper);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<userList.size();i++){
			ids.add(userList.get(i).getAboutId());
		}
		//1、待指派 2、进行时 3、待验收 4、已完成
		List<Integer> statuListNew = new ArrayList<Integer>();
		if(!po.getStatusList().isEmpty()) {
			//1、待指派 2、待接受 3、进行时 4、待验收 5、已完成
			for(int i=0;i<po.getStatusList().size();i++){
				switch(po.getStatusList().get(i)){
				case 1:
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
					+ "${2}.projectName,${3}.checkName", new MeasureProblemPO(),new JasoUserPO(),new ProjectPO(),new ProjectCheckTypePO());
			mul.where("${measure_problem}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.ge(po.getFinishedDateStart()!=null,"finishedDate", po.getFinishedDateStart())
				.le(po.getFinishedDateEnd()!=null,"finishedDate", po.getFinishedDateEnd())
				.ge(po.getBeignDateStart()!=null,"checkDate", po.getBeignDateStart())
				.le(po.getBeginDateEnd()!=null,"checkDate", po.getBeginDateEnd())
				.in("measureProblemId", ids)
				.in(!statuListNew.isEmpty(), "status", statuListNew);
			return serviceMain.mulSelect(mul);
		}
		return object;
	}
	public Object addAboutUserList(List<MeasureProblemUserPO> po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		for(int i=0;i<po.size();i++){
			po.get(i).setCompanyId(userInCache.getCompanyId());
		}
		return problemService.insertBatch(po);
	}
}
