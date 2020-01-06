package com.yaj.jaso.business.constructcontenttype.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.constructcontenttype.entity.po.ConstructContentTypePO;
import com.yaj.jaso.business.constructcontenttype.entity.vo.ConstructContentTypeVo;
import com.yaj.jaso.business.constructcontenttype.mapper.ConstructContentTypeMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-10
 */
@Service
public class ConstructContentTypeService extends ServiceImpl<ConstructContentTypeMapper, ConstructContentTypePO> {

    @Resource
    ConstructContentTypeMapper constructContentTypeMapper;
    public Object deleteLists(List<ConstructContentTypePO> list) {
		for(int i=0;i<list.size();i++){
			list.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(list);
	}

	public Object selectPageList(ConstructContentTypeVo constructContent) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<ConstructContentTypePO> page = new Page<ConstructContentTypePO>();
		page.setCurrent(constructContent.getPageVo().getPageNo());
		page.setSize(constructContent.getPageVo().getPageSize());
		Wrapper<ConstructContentTypePO> wrapper = new EntityWrapper<ConstructContentTypePO>();
		wrapper.orderDesc(Arrays.asList("construct_content_type_id"));
		wrapper.like(constructContent.getConstructContentTypeName()!=null,"construct_content_type_name", constructContent.getConstructContentTypeName());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(constructContent.getProjectId()!=null,"project_id", constructContent.getProjectId());
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object add(ConstructContentTypePO company) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		company.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(company);
	}

	public Object selectAllList(ConstructContentTypePO contentType) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ConstructContentTypePO> wrapper = new EntityWrapper<ConstructContentTypePO>();
		wrapper.orderDesc(Arrays.asList("construct_content_type_id"));
		wrapper.like(contentType.getConstructContentTypeName()!=null,"construct_content_type_name", contentType.getConstructContentTypeName());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(contentType.getProjectId()!=null,"project_id", contentType.getProjectId());
		return selectList(wrapper);
	}
}
