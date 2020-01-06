package com.yaj.jaso.business.measuresitepoint.service;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.measuresitepoint.entity.po.MeasureSitePointPO;
import com.yaj.jaso.business.measuresitepoint.mapper.MeasureSitePointMapper;

/*
 * @Description: 
 * @date: 2019-08-28
 */
@Service
public class MeasureSitePointService extends ServiceImpl<MeasureSitePointMapper, MeasureSitePointPO> {

    @Resource
    MeasureSitePointMapper measureSitePointMapper;

	public List<MeasureSitePointPO> selectLists(MeasureSitePointPO sitePointPO) {
		// TODO Auto-generated method stub
		JasoUserPO user = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MeasureSitePointPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", user.getCompanyId());
		wrapper.eq(sitePointPO.getProjectId()!=null,"project_id", sitePointPO.getProjectId());
		return selectList(wrapper);
	}

}
