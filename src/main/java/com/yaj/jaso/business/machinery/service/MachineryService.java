package com.yaj.jaso.business.machinery.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.machinery.entity.po.MachineryPO;
import com.yaj.jaso.business.machinery.entity.vo.MachineryVo;
import com.yaj.jaso.business.machinery.mapper.MachineryMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@Service
public class MachineryService extends ServiceImpl<MachineryMapper, MachineryPO> {

    @Resource
    MachineryMapper machineryMapper;
    public Object deleteLists(List<MachineryPO> list) {
		for(int i=0;i<list.size();i++){
			list.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(list);
	}

	public Object selectPageList(MachineryVo constructContent) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<MachineryPO> page = new Page<MachineryPO>();
		page.setCurrent(constructContent.getPageVo().getPageNo());
		page.setSize(constructContent.getPageVo().getPageSize());
		Wrapper<MachineryPO> wrapper = new EntityWrapper<MachineryPO>();
		wrapper.orderDesc(Arrays.asList("machinery_id"));
		wrapper.like(constructContent.getMachineryName()!=null,"machinery_name", constructContent.getMachineryName());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(constructContent.getProjectId()!=null,"project_id", constructContent.getProjectId());
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object add(MachineryPO machinery) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		machinery.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(machinery);
	}

	public Object selectMachineryList(MachineryPO machinery) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MachineryPO> wrapper = new EntityWrapper<MachineryPO>();
		wrapper.orderDesc(Arrays.asList("machinery_id"));
		wrapper.like(machinery.getMachineryName()!=null,"machinery_name", machinery.getMachineryName());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(machinery.getProjectId()!=null,"project_id", machinery.getProjectId());
		return selectList(wrapper);
	}
}
