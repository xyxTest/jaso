package com.yaj.jaso.business.qualitycheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.pojo.PageVo;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.examples.PushExample;
import com.yaj.examples.PushModel;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.service.JasoUserService;
import com.yaj.jaso.business.message.entity.po.MessagePO;
import com.yaj.jaso.business.message.service.MessageService;
import com.yaj.jaso.business.nature.entity.po.NaturePO;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.projectpaper.entity.po.ProjectPaperPO;
import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;
import com.yaj.jaso.business.qualitycheck.entity.vo.AboutUserInfo;
import com.yaj.jaso.business.qualitycheck.entity.vo.AboutUserList;
import com.yaj.jaso.business.qualitycheck.entity.vo.AcceptModel;
import com.yaj.jaso.business.qualitycheck.entity.vo.CheckList;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckAdd;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckCount;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckFind;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckResult;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckSend;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckVo;
import com.yaj.jaso.business.qualitycheck.mapper.QualityCheckMapper;
import com.yaj.jaso.business.qualitychecktype.entity.po.QualityCheckTypePO;
import com.yaj.jaso.business.qualitychecktype.service.QualityCheckTypeService;
import com.yaj.jaso.business.qualitycheckusers.entity.po.QualityCheckUsersPO;
import com.yaj.jaso.business.qualitycheckusers.service.QualityCheckUsersService;
import com.yaj.jaso.business.reply.entity.po.ReplyPO;
import com.yaj.jaso.business.reply.service.ReplyService;
import com.yaj.jaso.business.securitycheck.entity.vo.SecurityCheckDetail;
import com.yaj.jaso.business.securitycheck.entity.vo.SecurityCheckResult;

/*
 * @Description: 
 * @date: 2019-09-05
 */
@Service
public class QualityCheckService extends ServiceImpl<QualityCheckMapper, QualityCheckPO> {

    @Resource
    QualityCheckMapper qualityCheckMapper;
    @Autowired
    QualityCheckTypeService typeService;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    JasoUserService userService;
    @Autowired
    QualityCheckUsersService aboutUserService;
    @Autowired
    ReplyService replyService;
    @Autowired
    MessageService messageService;
    public Object add(QualityCheckAdd po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		QualityCheckPO quality = new QualityCheckPO();
		BeanUtil.copy(po, quality);
		quality.setCompanyId(userInCache.getCompanyId());
		quality.setJasoUserId(userInCache.getJasoUserId());
		if(po.getType()==1){
			quality.setStatus(5);
		}
		if(po.getType()==2){
			quality.setStatus(1);
		}
		if(insertOrUpdate(quality)){
			for(int i=0;i<po.getCheckTypeList().size();i++){
				po.getCheckTypeList().get(i).setAboutId(quality.getQualityCheckId());
				po.getCheckTypeList().get(i).setCompanyId(userInCache.getCompanyId());
				po.getCheckTypeList().get(i).setProjectId(po.getProjectId());
				po.getCheckTypeList().get(i).setType(1);
			}
			typeService.insertOrUpdateBatch(po.getCheckTypeList());
			if(po.getInformUserList()!=null){
				if(!po.getInformUserList().isEmpty()){
					List<QualityCheckUsersPO> pos = new ArrayList<>();
					for(int k=0;k<po.getInformUserList().size();k++){
						QualityCheckUsersPO qcu = new QualityCheckUsersPO();
						qcu.setProjectId(po.getProjectId());
						qcu.setCompanyId(userInCache.getCompanyId());
						qcu.setJasoUserId(po.getInformUserList().get(k).getJasoUserId());
						qcu.setAboutId(quality.getQualityCheckId());
						qcu.setType(1);
						qcu.setUserRealName(po.getInformUserList().get(k).getUserRealName());
						qcu.setUserType(3);
						qcu.setUniqueKey("13"+po.getInformUserList().get(k).getJasoUserId()+quality.getQualityCheckId());
						pos.add(qcu);
					}
					if(!aboutUserService.insertBatch(pos)){
						return false;
					}
				}
			}
		}else{
			return false;
		}
		return quality;
	}

	public Object deleteBatchByIds(List<QualityCheckPO> po) {
		// TODO Auto-generated method stub
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		return insertOrUpdateBatch(po);
	}

	public Object getDetail(QualityCheckPO po) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		QualityCheckResult result = new QualityCheckResult();
		///获取所有检查项
		if(po.getQualityCheckId()!=null){
			MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
					+ "${2}.projectName,${3}.paperUrl,${4}.natureName", new QualityCheckPO(),new JasoUserPO(),new ProjectPO(),new ProjectPaperPO(),new NaturePO());
			mul.where("${quality_check}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", cacheUser.getCompanyId())
				.eq("qualityCheckId", po.getQualityCheckId());
			List<Map> object=(List<Map>)serviceMain.mulSelect(mul);
			List<QualityCheckTypePO> checkTypeList = new ArrayList<>();
			Wrapper<QualityCheckTypePO> checkTypes = new EntityWrapper<>();
			checkTypes.eq("company_id", cacheUser.getCompanyId())
			.eq(po.getProjectId()!=null,"project_id", po.getProjectId())
			.eq("about_id", po.getQualityCheckId())
			.eq("type", 1);
			checkTypeList=typeService.selectList(checkTypes);
			//获取关联用户
			List<AboutUserInfo> aboutUsers = new ArrayList<>();
			aboutUsers=qualityCheckMapper.selectAboutUserDetail(cacheUser.getCompanyId(),po.getProjectId(),po.getQualityCheckId());
			result.setQualityCheck(object.get(0));
			List<AboutUserList> rectifyUsers = new ArrayList<>();
			for(int p=0;p<aboutUsers.size();p++){
				AboutUserList aboutuser = new AboutUserList();
				aboutuser.setUserRealName(aboutUsers.get(p).getUserRealName());
				aboutuser.setJasoUserId(aboutUsers.get(p).getJasoUserId());
				aboutuser.setUserType(aboutUsers.get(p).getUserType());//(1、整改人 2、参与人)
				aboutuser.setUserIcon(aboutUsers.get(p).getUserIcon());
				rectifyUsers.add(aboutuser);
			}
			result.setAboutUserList(rectifyUsers);
			List<QualityCheckTypePO> list = new ArrayList<>();
			for(int j=0;j<checkTypeList.size();j++){
				list.add(checkTypeList.get(j));
			}
			result.setCheckTypeList(list);
		}
		return result;
	}

	public Object selectLists(QualityCheckVo po) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${2}.projectName", new QualityCheckPO(), new JasoUserPO(),new ProjectPO());
		mul.setPage(po.getPageVo().getPageNo(), po.getPageVo().getPageSize());
		mul.where("${quality_check}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", cacheUser.getCompanyId())
			.eq(po.getType()!=null,"type", po.getType())
			.eq(po.getState()!=null,"state", po.getState())
			.eq(po.getStatus()!=null,"status", po.getStatus());
		return serviceMain.mulSelect(mul);
	}

	public Object send(QualityCheckSend po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		QualityCheckPO newpo = new QualityCheckPO();
		if(po.getQualityCheckId()!=null){
			newpo=selectById(po.getQualityCheckId());
			if(newpo!=null){
				newpo.setStartDate(new Date());
				newpo.setStatus(2);
				newpo.setState(po.getState());
				newpo.setFinishedDate(po.getFinishedDate());
				List<QualityCheckUsersPO> aboutUsers = new ArrayList<>();
				if(!po.getParticipantsList().isEmpty()){
					for(int i=0;i<po.getParticipantsList().size();i++){
						QualityCheckUsersPO aboutUser = new QualityCheckUsersPO();
						aboutUser.setJasoUserId(po.getParticipantsList().get(i).getJasoUserId());
						aboutUser.setCompanyId(cacheUser.getCompanyId());
						aboutUser.setType(1);
						aboutUser.setProjectId(po.getProjectId());
						aboutUser.setAboutId(po.getQualityCheckId());
						aboutUser.setUserRealName(po.getParticipantsList().get(i).getUserRealName());
						aboutUser.setUserType(2);
						aboutUsers.add(aboutUser);
					}
				}
				if(!po.getRectifyUserList().isEmpty()){
					for(int j=0;j<po.getRectifyUserList().size();j++){
						if(j>1){
							break;
						}
						QualityCheckUsersPO aboutUser = new QualityCheckUsersPO();
						aboutUser.setJasoUserId(po.getRectifyUserList().get(j).getJasoUserId());
						aboutUser.setCompanyId(cacheUser.getCompanyId());
						aboutUser.setType(1);
						aboutUser.setProjectId(po.getProjectId());
						aboutUser.setAboutId(po.getQualityCheckId());
						aboutUser.setUserRealName(po.getRectifyUserList().get(j).getUserRealName());
						aboutUser.setUserType(1);
						aboutUsers.add(aboutUser);
					}
				}
				if(insertOrUpdate(newpo)){
					if(!aboutUserService.insertOrUpdateBatch(aboutUsers)){
						return false;
					}else{
						/////////////////////推送整改人员
						List<MessagePO> messageList = new ArrayList<>();
						List<String> userIds = new ArrayList<>();
						for(int i=0;i<aboutUsers.size();i++){
							if(aboutUsers.get(i).getUserType()==1){
								MessagePO message = new MessagePO();
								message.setAboutId(newpo.getQualityCheckId());
								message.setTitle(cacheUser.getUserRealName()+"指派了一个质量整改单待你接受！");
								message.setState(0);
								message.setCreateUserId(cacheUser.getJasoUserId());
								message.setContent(newpo.getQualityCheckName());
								message.setType(1);
								message.setJasoUserId(aboutUsers.get(i).getJasoUserId());
								message.setCompanyId(newpo.getCompanyId());
								messageList.add(message);
								userIds.add(aboutUsers.get(i).getJasoUserId().toString());
							}
						}
						if(!messageList.isEmpty()){
							if(messageService.insertBatch(messageList)){
								PushModel push = new PushModel();
								PushExample pushExample = new PushExample();
						    	push.setContent(cacheUser.getUserRealName()+"指派了一个质量整改单待你接受！");
						    	push.setTitle("质量单状态推送");
						    	pushExample.testSendPushWithCustomConfig(push,userIds);
							}
						}
					}
				}else{
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}

	public Object check(QualityCheckPO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getType()!=null){
			if(po.getType()==1){
				po.setStatus(5);
			}
			if(po.getType()==2){
				po.setStatus(3);
				po.setProcess(0);
			}
		}
		if(insertOrUpdate(po)){
			ReplyPO reply = new ReplyPO();
			reply.setAboutId(po.getQualityCheckId());
			reply.setProjectId(po.getProjectId());
			if(po.getType()==1){
				reply.setColorState(2);
				reply.setReplyContent("验收通过！");
			}else if(po.getType()==2){
				reply.setColorState(3);
				reply.setReplyContent("验收不通过！");
			}
			reply.setJasoUserId(cacheUser.getJasoUserId());
			reply.setReplyType(2);
			reply.setCompanyId(cacheUser.getCompanyId());
			if(replyService.insertOrUpdate(reply)){
				Wrapper<QualityCheckUsersPO> qualityUsers = new EntityWrapper<>();
				qualityUsers.eq("user_type", 1)
				.eq("type", 1)
				.eq("about_id", po.getQualityCheckId());
				List<QualityCheckUsersPO> userList = new ArrayList<>();
				userList=aboutUserService.selectList(qualityUsers);
				if(!userList.isEmpty()){
					List<String> userIds = new ArrayList<>();
					List<MessagePO>	messageList = new ArrayList<>();
					for(int i=0;i<userList.size();i++){
						MessagePO message = new MessagePO();
						message.setAboutId(po.getQualityCheckId());
						message.setCompanyId(po.getCompanyId());
						message.setContent(po.getQualityCheckName());
						message.setState(0);
						message.setCreateUserId(cacheUser.getJasoUserId());
						message.setJasoUserId(userList.get(i).getJasoUserId());
						message.setType(1);
						message.setTitle("你整改的质量单已被验收！");
						messageList.add(message);
						userIds.add(userList.get(i).getJasoUserId().toString());
					}
					if(!messageList.isEmpty()){
						if(messageService.insertBatch(messageList)){
							PushModel push = new PushModel();
							PushExample pushExample = new PushExample();
					    	push.setContent("你整改的质量单已被验收！");
					    	push.setTitle("质量单状态推送");
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

	public QualityCheckCount count(QualityCheckPO po) {
		// TODO Auto-generated method stub
		QualityCheckCount count = new QualityCheckCount();
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<QualityCheckPO> wrapper = new EntityWrapper<>();
		//待我验收数据统计
		wrapper.eq("status", 4)
		.eq("jaso_user_id", cacheUser.getJasoUserId())
		.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		List<QualityCheckPO> gets = new ArrayList<QualityCheckPO>();
		gets=selectList(wrapper);
		count.setCheckNum(gets.size());
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<gets.size();i++){
			ids.add(gets.get(i).getQualityCheckId());
		}
		count.setCheckQualityIds(ids);
		//待我整改数据统计
		Wrapper<QualityCheckUsersPO> wrappers = new EntityWrapper<>();
		wrappers.eq("user_type", 1)
		.eq("type", 1)
		.eq("jaso_user_id", cacheUser.getJasoUserId())
		.eq(po.getProjectId()!=null,"project_id", po.getProjectId())
		.groupBy("about_id");
		List<QualityCheckUsersPO> gets2 = new ArrayList<QualityCheckUsersPO>();
		gets2=aboutUserService.selectList(wrappers);
		List<QualityCheckPO> checkLists = new ArrayList<>();
		List<QualityCheckPO> checkLists2 = new ArrayList<>();
		Wrapper<QualityCheckPO> wrapperpo = new EntityWrapper<>();
		List<Integer> statusList = new ArrayList<>();
		statusList.add(2);
		statusList.add(3);
		wrapperpo.in("status", statusList)
		.eq("project_id", po.getProjectId())
		.eq("company_id", cacheUser.getCompanyId());
		checkLists=selectList(wrapperpo);
		for(int i=0;i<checkLists.size();i++){
			boolean flag = false;
			for(int j=0;j<gets2.size();j++){
				if(gets2.get(j).getAboutId().equals(checkLists.get(i).getQualityCheckId())){
					flag = true;
				}
			}
			if(flag){
				checkLists2.add(checkLists.get(i));
			}
		}
		count.setRectifyNum(checkLists2.size());
		List<Long> ids2 = new ArrayList<>();
		for(int i=0;i<gets2.size();i++){
			ids2.add(gets2.get(i).getAboutId());
		}
		count.setRectifyQualityIds(ids2);
		return count;
	}

	public Object updateOrAccept(AcceptModel po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getQualityCheckId()!=null){
			QualityCheckPO old = new QualityCheckPO();
			old = selectById(po.getQualityCheckId());
			ReplyPO reply = new ReplyPO();
			reply.setAboutId(old.getQualityCheckId());
			reply.setJasoUserId(cacheUser.getJasoUserId());
			reply.setCompanyId(old.getCompanyId());
			reply.setProjectId(old.getProjectId());
			reply.setReplyType(2);
			if(po.getIsAccept()!=null){
				if(po.getIsAccept()==1){
					old.setStatus(3);
					reply.setReplyContent(cacheUser.getUserRealName()+"接受了该检查单整改指派！");
					reply.setColorState(2);
				}
				if(po.getIsAccept()==2){
					Wrapper<QualityCheckUsersPO> wrapper = new EntityWrapper<>();
					wrapper.eq("about_id",po.getQualityCheckId())
					.eq("type", 1)
					.eq("jaso_user_id", cacheUser.getJasoUserId())
					.eq("company_id", cacheUser.getCompanyId())
					.eq("user_type", 1);
					List<QualityCheckUsersPO> userList = new ArrayList<>();
					userList=aboutUserService.selectList(wrapper);
					for(int i=0;i<userList.size();i++){
						userList.get(i).setIfDelete(1);;
					}
					aboutUserService.insertOrUpdateBatch(userList);
					reply.setReplyContent(cacheUser.getUserRealName()+"拒绝了该检查单整改指派！");
					reply.setColorState(3);
					old.setStatus(1);
				}
			}
			replyService.insertOrUpdate(reply);
			if(po.getState()!=null){
				old.setState(po.getState());
			}
			if(insertOrUpdate(old)){
				if(po.getIsAccept()!=null){
					/////////////////////推送创建人员
					List<MessagePO> messageList = new ArrayList<>();
					List<String> userIds = new ArrayList<>();
					MessagePO message = new MessagePO();
					if(po.getIsAccept()==1){
						message.setTitle(cacheUser.getUserRealName()+"接受了你指派的一个质量整改单");
					}else{
						message.setTitle(cacheUser.getUserRealName()+"拒绝了你指派的一个质量整改单");
					}
					message.setAboutId(old.getQualityCheckId());
					message.setContent(old.getQualityCheckName());
					message.setState(0);
					message.setCreateUserId(cacheUser.getJasoUserId());
					message.setType(1);
					message.setJasoUserId(old.getJasoUserId());
					message.setCompanyId(old.getCompanyId());
					messageList.add(message);
					userIds.add(old.getJasoUserId().toString());
					if(!messageList.isEmpty()){
						if(messageService.insertBatch(messageList)){
							PushModel push = new PushModel();
							PushExample pushExample = new PushExample();
					    	push.setContent(message.getTitle());
					    	push.setTitle("质量单状态推送");
					    	//List<String> userIds = new ArrayList<>();
					    	pushExample.testSendPushWithCustomConfig(push,userIds);
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	public Object setProcess(QualityCheckPO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getQualityCheckId()!=null){
			QualityCheckPO old = new QualityCheckPO();
			old = selectById(po.getQualityCheckId());
			old.setProcess(po.getProcess());
			if(po.getProcess()==100){
				old.setStatus(4);
			}
			if(insertOrUpdate(old)){
				ReplyPO reply = new ReplyPO();
				reply.setAboutId(old.getQualityCheckId());
				reply.setCompanyId(cacheUser.getCompanyId());
				reply.setProjectId(po.getProjectId());
				reply.setColorState(2);
				reply.setJasoUserId(cacheUser.getJasoUserId());
				reply.setReplyContent("整改进度更新："+po.getProcess()+"%");
				reply.setReplyType(2);
				if(replyService.insertOrUpdate(reply)){
					///////////整改进度推送创建人
					List<MessagePO> messageList = new ArrayList<>();
					List<String> userIds = new ArrayList<>();
					MessagePO message = new MessagePO();
					message.setTitle(cacheUser.getUserRealName()+"设置质量整改单进度为"+po.getProcess()+"%");
					message.setAboutId(old.getQualityCheckId());
					message.setState(0);
					message.setCreateUserId(cacheUser.getJasoUserId());
					message.setContent(old.getQualityCheckName());
					message.setType(1);
					message.setJasoUserId(old.getJasoUserId());
					message.setCompanyId(old.getCompanyId());
					messageList.add(message);
					userIds.add(old.getJasoUserId().toString());
					if(!messageList.isEmpty()){
						if(messageService.insertBatch(messageList)){
							PushModel push = new PushModel();
							PushExample pushExample = new PushExample();
					    	push.setContent(cacheUser.getUserRealName()+"设置质量整改单进度为"+po.getProcess()+"%");
					    	push.setTitle("质量单状态推送");
					    	//List<String> userIds = new ArrayList<>();
					    	pushExample.testSendPushWithCustomConfig(push,userIds);
						}
					}
				}
			}
			
		}
		return true;
	}
	//我负责的获取
	public Object selectMyDutyList(QualityCheckFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Map<String,Object>> object = new ArrayList<Map<String,Object>>();
		Wrapper<QualityCheckUsersPO> userWrapper = new EntityWrapper<>();
		userWrapper.eq(po.getProjectId()!=null, "project_id", po.getProjectId())
		.eq("company_id", userInCache.getCompanyId())
		.eq("type", 1)
		.eq("user_type", 1)
		.eq("jaso_user_id", userInCache.getJasoUserId());
		List<QualityCheckUsersPO> userList= new ArrayList<>();
		userList=aboutUserService.selectList(userWrapper);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<userList.size();i++){
			ids.add(userList.get(i).getAboutId());
		}
		if(!ids.isEmpty()){
			MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
					+ "${2}.projectName,${3}.paperUrl,${4}.natureName", new QualityCheckPO(),new JasoUserPO(),new ProjectPO(),new ProjectPaperPO(),new NaturePO());
			mul.where("${quality_check}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.in("qualityCheckId", ids);
			return serviceMain.mulSelect(mul);
		}
		return object;
	}
	//我分派的
	public Object selectMyOwnList(QualityCheckFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
				+ "${2}.projectName,${3}.paperUrl,${4}.natureName", new QualityCheckPO(),new JasoUserPO(),new ProjectPO(),new ProjectPaperPO(),new NaturePO());
		mul.where("${quality_check}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", userInCache.getCompanyId())
			.eq("jasoUserId", userInCache.getJasoUserId());
		return serviceMain.mulSelect(mul);
	}
	//我参与的
	public Object selectMyJoinList(QualityCheckFind po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Map<String,Object>> object = new ArrayList<Map<String,Object>>();
		Wrapper<QualityCheckUsersPO> userWrapper = new EntityWrapper<>();
		userWrapper.eq(po.getProjectId()!=null, "project_id", po.getProjectId())
		.eq("company_id", userInCache.getCompanyId())
		.eq("type", 1)
		.eq("jaso_user_id", userInCache.getJasoUserId());
		List<QualityCheckUsersPO> userList= new ArrayList<>();
		userList=aboutUserService.selectList(userWrapper);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<userList.size();i++){
			ids.add(userList.get(i).getAboutId());
		}
		if(!ids.isEmpty()){
			MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
					+ "${2}.projectName", new QualityCheckPO(),new JasoUserPO(),new ProjectPO());
			mul.where("${quality_check}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", userInCache.getCompanyId())
				.in("qualityCheckId", ids);
			return serviceMain.mulSelect(mul);
		}
		return object;
	}

	public Object addAboutUserList(List<QualityCheckUsersPO> po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		for(int i=0;i<po.size();i++){
			po.get(i).setCompanyId(userInCache.getCompanyId());
			po.get(i).setUniqueKey(po.get(i).getType().toString()+po.get(i).getUserType().toString()+po.get(i).getJasoUserId()+po.get(i).getAboutId());
		}
		return aboutUserService.insertBatch(po);
	}

	public Object selectPageList(QualityCheckVo po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<QualityCheckResult> results = new ArrayList<>();
		java.sql.Date start=null;
		java.sql.Date end=null;
		Long jasoUserId=null;
		List<Integer> statusList = new ArrayList<>();//整改单状态列表
		if(po.getIsCheck()==1){//是检查单查询
			jasoUserId=cacheUser.getJasoUserId();
		}else if(po.getIsCheck()==2){//是整改单查询
			 //3、进行中 4、待验收 5、已完成
			statusList.add(3);
			statusList.add(4);
			statusList.add(5);
		}
		if(po.getIds()!=null && po.getIds().isEmpty()){
			return results;
		}else{
			statusList.clear();
		}
		if(po.getStart()!=null){
			start=new java.sql.Date(po.getStart().getTime());
		}
		if(po.getEnd()!=null){
			end=new java.sql.Date(po.getEnd().getTime());
		}
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,"
				+ "${2}.projectName,${3}.paperUrl,${4}.natureName", new QualityCheckPO(),new JasoUserPO(),new ProjectPO(),new ProjectPaperPO(),new NaturePO());
		mul.setPage(po.getPageVo().getPageNo(), po.getPageVo().getPageSize());
		mul.where("${quality_check}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", cacheUser.getCompanyId())
			.eq(po.getNatureId()!=null,"natureId", po.getNatureId())
			.eq(po.getType()!=null,"type", po.getType())
			.eq(po.getStatus()!=null, "status", po.getStatus())
			.like(po.getQualityCheckName()!=null,"qualityCheckName", po.getQualityCheckName())
			.eq(jasoUserId!=null,"jasoUserId", jasoUserId)
			.ge(start!=null,"startDate",start)
			.le(end!=null,"startDate", end)
			.in(!statusList.isEmpty(),"status", statusList)
			.in(po.getIds()!=null,"qualityCheckId", po.getIds());
		mul.setOrderBy("qualitycheck.create_time desc");
		Map<String, Object> getDatas=(Map<String, Object>) serviceMain.mulSelect(mul);
		List<Map<String,Object>> test = (List<Map<String, Object>>) getDatas.get("data");
		
		List<Long> checkIds = new ArrayList<>();
		for(Map<String,Object> item:test){
			checkIds.add(Long.valueOf(item.get("qualityCheckId").toString()));
		}
		//获取关联用户
		if(!checkIds.isEmpty()){
			MulSelect mulUser = MulSelect.newInstance("${1}.userIcon", new QualityCheckUsersPO(),new JasoUserPO());
			mulUser.where("${quality_check_users}")
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq("companyId", cacheUser.getCompanyId())
				.eq("type", 1)
				.in("aboutId", checkIds);
			List<Map<String,Object>> getDatasUser=(List<Map<String, Object>>) serviceMain.mulSelect(mulUser);
			for(Map<String,Object> item:test){
				Long securityCheckId = Long.valueOf(item.get("qualityCheckId").toString());
				List<AboutUserList> aboutUserList = new ArrayList<>();
				QualityCheckResult resultItem = new QualityCheckResult();
				resultItem.setQualityCheck(item);
				resultItem.setPageInfo(getDatas.get("page"));
				for(Map<String,Object> items:getDatasUser){
					Long aboutId=Long.valueOf(items.get("aboutId").toString());
					if(aboutId.equals(securityCheckId)){
						AboutUserList aboutUser = new AboutUserList();
						aboutUser.setJasoUserId(Long.valueOf(items.get("jasoUserId").toString()));
						aboutUser.setUserIcon(items.get("userIcon").toString());
						aboutUser.setUserRealName(items.get("userRealName").toString());
						aboutUser.setUserType(Integer.valueOf(items.get("userType").toString()));
						aboutUserList.add(aboutUser);
					}
				}
				resultItem.setAboutUserList(aboutUserList);
				results.add(resultItem);
			}
		}else {
			for(Map<String,Object> item:test){
				List<AboutUserList> aboutUserList = new ArrayList<>();
				QualityCheckResult resultItem = new QualityCheckResult();
				resultItem.setAboutUserList(aboutUserList);
				results.add(resultItem);
			}
		}
		return results;
	}

	public Object getList(QualityCheckPO po) {
		// TODO Auto-generated method stub
		List<QualityCheckPO> checkList = new ArrayList<>();
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<QualityCheckPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId())
		.eq(po.getProjectId()!=null,"project_id", po.getProjectId())
		.eq("jaso_user_id",userInCache.getJasoUserId());
		checkList = selectList(wrapper);
		List<CheckList> check = new ArrayList<>();
		for(int i=0;i<checkList.size();i++){
			CheckList item = new CheckList();
			item.setQualityCheckId(checkList.get(i).getQualityCheckId());
			item.setName(checkList.get(i).getQualityCheckName());
			check.add(item);
		}
		return check;
	}
	
}
