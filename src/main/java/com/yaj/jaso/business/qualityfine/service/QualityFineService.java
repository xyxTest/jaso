package com.yaj.jaso.business.qualityfine.service;

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
import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;
import com.yaj.jaso.business.qualitycheck.service.QualityCheckService;
import com.yaj.jaso.business.qualityfine.entity.po.QualityFinePO;
import com.yaj.jaso.business.qualityfine.entity.vo.QualityFineAdd;
import com.yaj.jaso.business.qualityfine.entity.vo.QualityFineResult;
import com.yaj.jaso.business.qualityfine.entity.vo.QualityFineVo;
import com.yaj.jaso.business.qualityfine.mapper.QualityFineMapper;

/*
 * @Description: 质量奖惩单
 * @date: 2019-09-07
 */
@Service
public class QualityFineService extends ServiceImpl<QualityFineMapper, QualityFinePO> {

    @Resource
    QualityFineMapper qualityFineMapper;
    @Autowired
    FineAboutService fineAboutService;
    @Autowired
    JasoUserService userService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    QualityCheckService qualityCheckService;
    @Autowired
    ServiceMain serviceMain;
	public Object deleteBatchByIds(List<QualityFinePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectPageList(QualityFineVo pt) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Long> checkIds  = new ArrayList<>();
		if(pt.getQualityCheckName()!=null){
			List<QualityCheckPO> qualityCheckList = new ArrayList<>();
			Wrapper<QualityCheckPO> checkWrapper = new EntityWrapper<>();
			checkWrapper.eq("company_id", userInCache.getCompanyId())
			.eq(pt.getProjectId()!=null,"project_id", pt.getProjectId())
			.like("quality_check_name", pt.getQualityCheckName());
			qualityCheckList = qualityCheckService.selectList(checkWrapper);
			for(QualityCheckPO po:qualityCheckList){
				checkIds.add(po.getQualityCheckId());
			}
			if(checkIds.isEmpty()){
				return checkIds;
			}
		}
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${2}.projectName,${3}.qualityCheckName", new QualityFinePO(), new JasoUserPO(),new ProjectPO(),new QualityCheckPO());
		mul.setPage(pt.getPageVo().getPageNo(), pt.getPageVo().getPageSize());
		mul.where("${quality_fine}")
			.eq(pt.getProjectId()!=null,"projectId", pt.getProjectId())
			.eq("companyId", userInCache.getCompanyId())
			.eq(pt.getType()!=null,"type",pt.getType())
			.eq(pt.getQualityCheckId()!=null,"qualityCheckId", pt.getQualityCheckId())
			.in(!checkIds.isEmpty(),"qualityCheckId", checkIds);
		mul.setOrderBy("qualityfine.create_time desc");
		return serviceMain.mulSelect(mul);
	}

	public Object add(QualityFineAdd po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		QualityFinePO newpo = new QualityFinePO();
		BeanUtil.copy(po, newpo);
		newpo.setCompanyId(userInCache.getCompanyId());
		newpo.setJasoUserId(userInCache.getJasoUserId());
		if(insertOrUpdate(newpo)){
			if(!po.getAboutIds().isEmpty()){
				List<FineAboutPO> abouts = new ArrayList<>();
				for(int i=0;i<po.getAboutIds().size();i++){
					FineAboutPO fine = new FineAboutPO();
					fine.setAboutId(po.getAboutIds().get(i).getAboutId());
					fine.setType(1);
					fine.setProjectId(po.getProjectId());
					fine.setQualityOrSecurityId(newpo.getQualityFineId());
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
	public Object selectById(QualityFinePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		QualityFineResult result = new QualityFineResult();
		List<String> userRealNames = new ArrayList<>();
		List<String> departments = new ArrayList<>();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${2}.projectName", new QualityFinePO(), new JasoUserPO(),new ProjectPO());
		mul.where("${quality_fine}")
			.eq(po.getQualityFineId()!=null,"qualityFineId", po.getQualityFineId())
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", userInCache.getCompanyId());
		List<Map> ss = (List<Map>)serviceMain.mulSelect(mul);
		if(!ss.isEmpty()){
			result.setQualityFine(ss.get(0));
			QualityCheckPO check = qualityCheckService.selectById(po.getQualityCheckId());
			if(check!=null){
				result.setCause(check.getQualityCheckName());
			}
		}
		Wrapper<FineAboutPO> about = new EntityWrapper<>();
		about.eq("quality_or_security_id", po.getQualityFineId())
		.eq("type", 1).eq("company_id", userInCache.getCompanyId());
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
