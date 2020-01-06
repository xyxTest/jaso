package com.yaj.jaso.business.securityfine.service;


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
import com.yaj.jaso.business.department.entity.po.DepartmentPO;
import com.yaj.jaso.business.department.service.DepartmentService;
import com.yaj.jaso.business.fineabout.entity.po.FineAboutPO;
import com.yaj.jaso.business.fineabout.service.FineAboutService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.service.JasoUserService;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.securitycheck.entity.po.SecurityCheckPO;
import com.yaj.jaso.business.securitycheck.service.SecurityCheckService;
import com.yaj.jaso.business.securityfine.entity.po.SecurityFinePO;
import com.yaj.jaso.business.securityfine.entity.vo.SecurityFineAdd;
import com.yaj.jaso.business.securityfine.entity.vo.SecurityFineResult;
import com.yaj.jaso.business.securityfine.entity.vo.SecurityFineVo;
import com.yaj.jaso.business.securityfine.mapper.SecurityFineMapper;

/*
 * @Description: 质量奖惩单
 * @date: 2019-09-07
 */
@Service
public class SecurityFineService extends ServiceImpl<SecurityFineMapper, SecurityFinePO> {

    @Resource
    SecurityFineMapper securityFineMapper;
    @Autowired
    FineAboutService fineAboutService;
    @Autowired
    JasoUserService userService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    SecurityCheckService securityCheckService;
    @Autowired
    ServiceMain serviceMain;
	public Object deleteBatchByIds(List<SecurityFinePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectPageList(SecurityFineVo pt) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Long> checkIds  = new ArrayList<>();
		if(pt.getSecurityCheckName()!=null){
			List<SecurityCheckPO> qualityCheckList = new ArrayList<>();
			Wrapper<SecurityCheckPO> checkWrapper = new EntityWrapper<>();
			checkWrapper.eq("company_id", userInCache.getCompanyId())
			.eq(pt.getProjectId()!=null,"project_id", pt.getProjectId())
			.like("security_check_name", pt.getSecurityCheckName());
			qualityCheckList = securityCheckService.selectList(checkWrapper);
			for(SecurityCheckPO po:qualityCheckList){
				checkIds.add(po.getSecurityCheckId());
			}
			if(checkIds.isEmpty()){
				return checkIds;
			}
		}
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${2}.projectName,${3}.securityCheckName", new SecurityFinePO(), new JasoUserPO(),new ProjectPO(),new SecurityCheckPO());
		mul.setPage(pt.getPageVo().getPageNo(), pt.getPageVo().getPageSize());
		mul.where("${security_fine}")
			.eq(pt.getProjectId()!=null,"projectId", pt.getProjectId())
			.eq("companyId", userInCache.getCompanyId())
			.eq(pt.getType()!=null,"type",pt.getType())
			.eq(pt.getSecurityCheckId()!=null,"securityCheckId", pt.getSecurityCheckId())
			.in(!checkIds.isEmpty(),"securityCheckId", checkIds);
		mul.setOrderBy("securityfine.create_time desc");
		return serviceMain.mulSelect(mul);
	}

	public Object add(SecurityFineAdd po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		SecurityFinePO newpo = new SecurityFinePO();
		BeanUtil.copy(po,newpo );
		newpo.setCompanyId(userInCache.getCompanyId());
		newpo.setJasoUserId(userInCache.getJasoUserId());
		if(insertOrUpdate(newpo)){
			if(!po.getAboutIds().isEmpty()){
				List<FineAboutPO> abouts = new ArrayList<>();
				for(int i=0;i<po.getAboutIds().size();i++){
					FineAboutPO fine = new FineAboutPO();
					fine.setAboutId(po.getAboutIds().get(i).getAboutId());
					fine.setType(2);
					fine.setProjectId(po.getProjectId());
					fine.setQualityOrSecurityId(newpo.getSecurityFineId());
					fine.setState(po.getAboutIds().get(i).getState());
					fine.setCompanyId(userInCache.getCompanyId());
					abouts.add(fine);
				}
				fineAboutService.insertOrUpdateBatch(abouts);
			}
		}else{
			return false;
		}
		return true;
	}

	public Object selectById(SecurityFinePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		SecurityFineResult result = new SecurityFineResult();
		List<String> userRealNames = new ArrayList<>();
		List<String> departments = new ArrayList<>();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${2}.projectName", new SecurityFinePO(), new JasoUserPO(),new ProjectPO());
		mul.where("${security_fine}")
			.eq(po.getSecurityFineId()!=null,"securityFineId", po.getSecurityFineId())
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", userInCache.getCompanyId());
		List<Map> ss = (List<Map>)serviceMain.mulSelect(mul);
		if(!ss.isEmpty()){
			result.setSecurityFine(ss.get(0));
			SecurityCheckPO check = securityCheckService.selectById(po.getSecurityCheckId());
			if(check!=null){
				result.setCause(check.getSecurityCheckName());
			}
		}
		Wrapper<FineAboutPO> about = new EntityWrapper<>();
		about.eq("quality_or_security_id", po.getSecurityFineId())
		.eq("type", 2).eq("company_id", userInCache.getCompanyId());
		List<FineAboutPO> aboutList = new ArrayList<>();
		aboutList=fineAboutService.selectList(about);
		List<Long> aboutUserIds = new ArrayList<>();
		List<Long> aboutDepartmentIds = new ArrayList<>();
		for(FineAboutPO pos:aboutList){
			if(pos.getState()==1){
				aboutUserIds.add(pos.getAboutId());
			}
			if(pos.getState()==2){
				aboutDepartmentIds.add(pos.getAboutId());
			}
		}
		if(!aboutUserIds.isEmpty()){
			Wrapper<JasoUserPO> user = new EntityWrapper<>();
			user.in("jaso_user_id", aboutUserIds);
			List<JasoUserPO> userList = new ArrayList<>();
			userList=userService.selectList(user);
			for(JasoUserPO userItem:userList){
				userRealNames.add(userItem.getUserRealName());
			}
		}
		if(!aboutDepartmentIds.isEmpty()){
			Wrapper<DepartmentPO> department = new EntityWrapper<>();
			department.in("department_id", aboutDepartmentIds);
			List<DepartmentPO> departmentList = new ArrayList<>();
			departmentList=departmentService.selectList(department);
			for(DepartmentPO deparmtnetItem:departmentList){
				departments.add(deparmtnetItem.getDepartmentName());
			}
		}
		result.setAboutDepartmentNameList(departments);
		result.setAboutUserRealNameList(userRealNames);
		return result;
	}

}
