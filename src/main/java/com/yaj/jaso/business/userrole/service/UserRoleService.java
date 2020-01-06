package com.yaj.jaso.business.userrole.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.userrole.entity.po.UserRolePO;
import com.yaj.jaso.business.userrole.entity.vo.UserRoleVo;
import com.yaj.jaso.business.userrole.mapper.UserRoleMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRolePO> {

    @Resource
    UserRoleMapper userRoleMapper;
    public Object addUserRole(UserRolePO urp){
    	return insert(urp);
    }
    public Object deleteUserRole(UserRolePO urp){
    	Wrapper<UserRolePO> wrapper = new EntityWrapper<UserRolePO>();
    	if(urp.getCompanyId()!=null){
    		wrapper.eq("company_id", urp.getCompanyId());
    	}
    	if(urp.getRoleId()!=null){
    		wrapper.eq("role_id", urp.getRoleId());
    	}
    	if(urp.getJasoUserId()!=null){
    		wrapper.eq("jaso_user_id", urp.getJasoUserId());
    	}
    	return delete(wrapper);
    }
    public Object getUserRoleList(UserRoleVo urp){
    	Page<UserRolePO> page = new Page<UserRolePO>();
    	page.setCurrent(urp.getPageVo().getPageNo());
    	page.setSize(urp.getPageVo().getPageSize());
    	UserRolePO po = new UserRolePO();
    	BeanUtil.copy(urp, po);
    	Wrapper<UserRolePO> wrapper = new EntityWrapper<UserRolePO>();
    	if(urp.getCompanyId()!=null){
    		wrapper.eq("company_id", urp.getCompanyId());
    	}
    	if(urp.getRoleId()!=null){
    		wrapper.eq("role_id", urp.getRoleId());
    	}
    	if(urp.getJasoUserId()!=null){
    		wrapper.eq("jaso_user_id", urp.getJasoUserId());
    	}
    	page=selectPage(page, wrapper);
    	return PageVoUtil.setPage(page);
    }
}
