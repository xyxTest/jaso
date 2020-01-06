package com.yaj.jaso.business.nature.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.nature.entity.po.NaturePO;
import com.yaj.jaso.business.nature.entity.vo.NatureVo;
import com.yaj.jaso.business.nature.mapper.NatureMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-09-04
 */
@Service
public class NatureService extends ServiceImpl<NatureMapper, NaturePO> {

    @Resource
    NatureMapper natureMapper;

	public Object add(NaturePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setJasoUserId(userInCache.getJasoUserId());
		po.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<NaturePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(NatureVo po) {
		// TODO Auto-generated method stub
		Page<NaturePO> page = new Page<NaturePO>();
		page.setCurrent(po.getPageVo().getPageNo()); 
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<NaturePO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getType()!=null,"type", po.getType());
		wrapper.like(po.getNatureName()!=null,"nature_name", po.getNatureName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public List<NaturePO> selectLists(NaturePO po) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<NaturePO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getType()!=null,"type", po.getType());
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		return selectList(wrapper);
	}
}
