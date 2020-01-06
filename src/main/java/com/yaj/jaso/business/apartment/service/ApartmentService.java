package com.yaj.jaso.business.apartment.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.apartment.entity.po.ApartmentPO;
import com.yaj.jaso.business.apartment.mapper.ApartmentMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;

/*
 * @Description: 
 * @date: 2019-11-28
 */
@Service
public class ApartmentService extends ServiceImpl<ApartmentMapper, ApartmentPO> {

    @Resource
    ApartmentMapper apartmentMapper;

	public Object add(ApartmentPO apartment) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object deleteList(List<ApartmentPO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectPage(ApartmentPO log) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectAll(ApartmentPO log) {
		// TODO Auto-generated method stub
		JasoUserPO user = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<ApartmentPO> list = new ArrayList<>();
		Wrapper<ApartmentPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", user.getCompanyId())
		.eq(log.getProjectId()!=null,"project_id", log.getProjectId());
		list=selectList(wrapper);
		return list;
	}

}
