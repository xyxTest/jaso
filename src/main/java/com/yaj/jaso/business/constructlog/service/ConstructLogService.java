package com.yaj.jaso.business.constructlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.pojo.PageVo;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.constructlog.entity.po.ConstructLogPO;
import com.yaj.jaso.business.constructlog.entity.vo.ConstructLogList;
import com.yaj.jaso.business.constructlog.entity.vo.ConstructLogResult;
import com.yaj.jaso.business.constructlog.entity.vo.ConstructLogVo;
import com.yaj.jaso.business.constructlog.mapper.ConstructLogMapper;
import com.yaj.jaso.business.constructlogcontent.entity.po.ConstructLogContentPO;
import com.yaj.jaso.business.constructlogcontent.service.ConstructLogContentService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.projecttenders.entity.po.ProjectTendersPO;
import com.yaj.jaso.business.userdepartment.service.UserDepartmentService;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@Service
public class ConstructLogService extends ServiceImpl<ConstructLogMapper, ConstructLogPO> {

    @Resource
    ConstructLogMapper constructLogMapper;
    @Autowired
    ConstructLogContentService contentService;
    @Autowired
    UserDepartmentService userDepartmentService;
    @Autowired
    ServiceMain serviceMain;
	public Object add(ConstructLogList list) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		list.getConstructLog().setCompanyId(userInCache.getCompanyId());
		list.getConstructLog().setJasoUserId(userInCache.getJasoUserId());
		boolean result = true;
		if(list.getConstructLog()!=null){
			ConstructLogPO po = new ConstructLogPO();
			BeanUtil.copy(list.getConstructLog(), po);
			if(insertOrUpdate(po)){
				if(list.getContentList()!=null){
					List<ConstructLogContentPO> poList = new ArrayList<ConstructLogContentPO>();
					for(ConstructLogContentPO logPo : list.getContentList()){
						ConstructLogContentPO log = new ConstructLogContentPO();
						BeanUtil.copy(logPo, log);
						log.setProjectId(po.getProjectId());
						log.setCompanyId(userInCache.getCompanyId());
						log.setConstructLogId(po.getConstructLogId());
						poList.add(log);
					}
					if(!contentService.insertBatch(poList)){
						result=false;
					}
				}
			}else{
				result = false;
			}
		}
		return result;
	}
	public Object selectPageList(ConstructLogVo constructLog) {
		long startTime = System.currentTimeMillis(); //获取开始时间
		JasoUserPO jasoUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		// TODO Auto-generated method stub
		List<ConstructLogResult> resultList = new ArrayList<ConstructLogResult>();
		List<Long> userIds= new ArrayList<Long>();
		if(constructLog.getCreateUserName()!=null){
			String userRealName = "%"+constructLog.getCreateUserName()+"%";
			userIds=constructLogMapper.selectUserListByName(jasoUser.getCompanyId(),constructLog.getProjectId(),userRealName);
			if(userIds.isEmpty()){
				return resultList;
			}
		}
		if(constructLog.getJasoUserId()!=null){
			userIds.add(constructLog.getJasoUserId());
		}
		MulSelect mul = MulSelect.newInstance("${1}.projectName,${2}.tendersName,${3}.userRealName,${3}.userIcon", new ConstructLogPO(), new ProjectPO(),new ProjectTendersPO(),new JasoUserPO());
		mul.setPage(constructLog.getPageVo().getPageNo(), constructLog.getPageVo().getPageSize());
		java.sql.Date start=null;
		java.sql.Date end=null;
		if(constructLog.getStartTime()!=null){
			start=new java.sql.Date(constructLog.getStartTime().getTime());
		}
		if(constructLog.getEndTime()!=null){
			end=new java.sql.Date(constructLog.getEndTime().getTime());
		}
		mul.where("${construct_log}")
			.eq(constructLog.getProjectTendersId()!=null,"projectTendersId", constructLog.getProjectTendersId())
			.eq("companyId", jasoUser.getCompanyId())
			.eq(constructLog.getProjectId()!=null,"projectId", constructLog.getProjectId())
			.eq(constructLog.getProjectTendersId()!=null,"projectTendersId", constructLog.getProjectTendersId())
			.ge(start!=null,"constructDate",start)
			.le(end!=null,"constructDate",end)
			.in(!userIds.isEmpty(),"jasoUserId", userIds);
		mul.setOrderBy("constructlog.construct_date desc");		
		Map<String, Object> getDatas=(Map<String, Object>) serviceMain.mulSelect(mul);
		List<Map<String,Object>> test = (List<Map<String, Object>>) getDatas.get("data");
		List<ConstructLogContentPO> contentList = new ArrayList<>();
		List<Long> constructLogId = new ArrayList<>();
		for(Map<String,Object> item:test){
			constructLogId.add(Long.valueOf(item.get("constructLogId").toString()));
		}
		if(!constructLogId.isEmpty()){
			Wrapper<ConstructLogContentPO> wrapperContent = new EntityWrapper<ConstructLogContentPO>();
			wrapperContent.eq(constructLog.getProjectId()!=null,"project_id", constructLog.getProjectId())
			.eq("company_id", jasoUser.getCompanyId())
			.in("construct_log_id", constructLogId);
			contentList = contentService.selectList(wrapperContent);
		}
		
		for(Map<String,Object> item:test){
			ConstructLogResult sets = new ConstructLogResult();
			sets.setConstructLog(item);
			List<ConstructLogContentPO> contentLists = new ArrayList<>();
			for(int i=0;i<contentList.size();i++){
				Long ids = Long.valueOf(item.get("constructLogId").toString());
				if(contentList.get(i).getConstructLogId().equals(ids)){
					contentLists.add(contentList.get(i));
				}
			}
			sets.setContentList(contentLists);
			resultList.add(sets);
		}
		for(ConstructLogResult item:resultList){
			List<Integer> roleTypes=constructLogMapper.selectRoleTypeLists(jasoUser.getCompanyId(),item.getConstructLog().get("jasoUserId"));
			item.getConstructLog().put("roleTypes", roleTypes);
		}
		PageVo pageVo=(PageVo)(getDatas.get("page"));
		Page<ConstructLogResult> pages = new Page<ConstructLogResult>();
		pages.setCurrent(constructLog.getPageVo().getPageNo());
		pages.setSize(constructLog.getPageVo().getPageSize());
		pages.setTotal(pageVo.getTotal());
		pages.setRecords(resultList);
		long endTime = System.currentTimeMillis(); //获取开始时间
		System.out.println("耗时时间："+(endTime-startTime));
		return PageVoUtil.setPage(pages);
	}
	
	public Object deleteList(List<ConstructLogPO> constructLog) {
		// TODO Auto-generated method stub
		for(int i=0;i<constructLog.size();i++){
			constructLog.get(i).setIfDelete(1);
		}
		return updateBatchById(constructLog);
	}
	/*施工日志，获取当前用户的角色（班组长、工人、施工员、其他）*/
	public Object getRoleType() {
		// TODO Auto-generated method stub
		JasoUserPO jasoUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		return constructLogMapper.selectRoleTypeList(jasoUser.getCompanyId(),jasoUser.getJasoUserId());
	}
}
