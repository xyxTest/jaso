package com.yaj.jaso.business.subjectmaterial.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.subjectmaterial.entity.po.SubjectMaterialPO;
import com.yaj.jaso.business.subjectmaterial.entity.vo.SubjectMaterialVo;
import com.yaj.jaso.business.subjectmaterial.mapper.SubjectMaterialMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@Service
public class SubjectMaterialService extends ServiceImpl<SubjectMaterialMapper, SubjectMaterialPO> {

    @Resource
    SubjectMaterialMapper subjectMaterialMapper;
    public Object deleteLists(List<SubjectMaterialPO> list) {
		for(int i=0;i<list.size();i++){
			list.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(list);
	}

	public Object selectPageList(SubjectMaterialVo constructContent) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<SubjectMaterialPO> page = new Page<SubjectMaterialPO>();
		page.setCurrent(constructContent.getPageVo().getPageNo());
		page.setSize(constructContent.getPageVo().getPageSize());
		Wrapper<SubjectMaterialPO> wrapper = new EntityWrapper<SubjectMaterialPO>();
		wrapper.orderDesc(Arrays.asList("subject_material_id"));
		wrapper.like(constructContent.getSubjectMaterialName()!=null,"subject_material_name", constructContent.getSubjectMaterialName());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(constructContent.getProjectId()!=null,"project_id", constructContent.getProjectId());
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object add(SubjectMaterialPO machinery) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		machinery.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(machinery);
	}

	public Object getSubjectMaterialList(SubjectMaterialPO subjectMaterial) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<SubjectMaterialPO> wrapper = new EntityWrapper<SubjectMaterialPO>();
		wrapper.orderDesc(Arrays.asList("subject_material_id"));
		wrapper.like(subjectMaterial.getSubjectMaterialName()!=null,"subject_material_name", subjectMaterial.getSubjectMaterialName());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(subjectMaterial.getProjectId()!=null,"project_id", subjectMaterial.getProjectId());
		return selectList(wrapper);
	}
}
