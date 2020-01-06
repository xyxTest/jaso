package com.yaj.jaso.business.rolemenu.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.rolemenu.entity.po.RoleMenuPO;
import com.yaj.jaso.business.rolemenu.entity.vo.RoleMenuVo;
import com.yaj.jaso.business.rolemenu.mapper.RoleMenuMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenuPO> {

    @Resource
    RoleMenuMapper roleMenuMapper;

	public Object selectLists(RoleMenuVo rmp) {
		
		Page<RoleMenuPO> page = new Page<RoleMenuPO>();
		page.setCurrent(rmp.getPageVo().getPageNo());
		page.setSize(rmp.getPageVo().getPageSize());
		RoleMenuPO po = new RoleMenuPO();
		BeanUtil.copy(rmp, po);
		Wrapper<RoleMenuPO> wrapper = new EntityWrapper<RoleMenuPO>();
		if(rmp.getCompanyId()!=null){
			wrapper.eq("company_id", rmp.getCompanyId());
		}
		if(rmp.getRoleId()!=null){
			wrapper.eq("role_id", rmp.getRoleId());
		}
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

}
