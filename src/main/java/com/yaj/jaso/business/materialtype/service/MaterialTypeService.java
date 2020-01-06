package com.yaj.jaso.business.materialtype.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.jaso.business.materialtype.entity.MaterialTypePO;
import com.yaj.jaso.business.materialtype.entity.vo.MaterialTypeVo;
import com.yaj.jaso.business.materialtype.mapper.MaterialTypeMapper;
import com.yaj.jaso.global.PageVoUtil;
@Service
public class MaterialTypeService extends ServiceImpl<MaterialTypeMapper, MaterialTypePO> {
	@Resource
	MaterialTypeMapper materialTypeMapper;

	public MaterialTypePO getMaterialTypeByName(String materialType) {
		// TODO Auto-generated method stub
		Wrapper<MaterialTypePO> wrapper = new EntityWrapper<MaterialTypePO>();
		wrapper.eq("material_type_name", materialType);
		List<MaterialTypePO> getList = selectList(wrapper);
		if(!getList.isEmpty()){
			return getList.get(0);
		}
		return null;
	}

	public Object selectPageList(MaterialTypePO company) {
		Wrapper<MaterialTypePO>	mpo = new EntityWrapper<MaterialTypePO>();
		if(company.getCompanyId()!=null){
			mpo.eq("company_id", company.getCompanyId());
		}
		if(company.getProjectId()!=null){
			mpo.eq("project_id", company.getProjectId());
		}
		return selectList(mpo);
	}

	public Object selectPageLists(MaterialTypeVo company) {
		Page<MaterialTypePO> page = new Page<MaterialTypePO>();
		page.setSize(company.getPageVo().getPageSize());
		page.setCurrent(company.getPageVo().getPageNo());
		Wrapper<MaterialTypePO> wrapper = new EntityWrapper<MaterialTypePO>();
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
		
	}

	public Object deleteTypeList(List<MaterialTypePO> company) {
		// TODO Auto-generated method stub
		for(int i=0;i<company.size();i++){
			company.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(company);
	}
}
