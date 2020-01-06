package com.yaj.jaso.business.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.role.entity.po.RolePO;
import com.yaj.jaso.business.role.entity.vo.RoleSetting;
import com.yaj.jaso.business.role.entity.vo.RoleVo;
import com.yaj.jaso.business.role.mapper.RoleMapper;
import com.yaj.jaso.business.rolemenu.entity.po.RoleMenuPO;
import com.yaj.jaso.business.rolemenu.service.RoleMenuService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.menu.entity.po.MenuPO;
import com.yaj.jaso.business.userrole.entity.po.UserRolePO;
import com.yaj.jaso.business.userrole.service.UserRoleService;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper,RolePO> {

    @Resource
    RoleMapper roleMapper;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleMenuService menuRoleService;
    @Autowired
    ServiceMain serviceMain;
    //该模块只能超级管理员调用
	public Object selectList(RoleVo rpo) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<RolePO> page = new Page<RolePO>();
		page.setCurrent(rpo.getPageVo().getPageNo()); 
		page.setSize(rpo.getPageVo().getPageSize());
		Wrapper<RolePO> wrapper = new EntityWrapper<>();
		wrapper.like(rpo.getRoleName()!=null,"role_name", rpo.getRoleName())
		.eq("company_id", cacheUser.getCompanyId());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object selectRoleList(RolePO po) {
		// TODO Auto-generated method stub
		JasoUserPO currentUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<RolePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", currentUser.getCompanyId());
		return selectList(wrapper);
		
	}

	public Object deleteList(List<RolePO> rpo) {
		for(int i=0;i<rpo.size();i++){
			rpo.get(i).setIfDelete(1);
		}
		return updateBatchById(rpo);
	}

	public Object add(RolePO rpo) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		rpo.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(rpo);
	}

	public Object roleSet(RoleSetting po) {
		JasoUserPO currentUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Long companyId=currentUser.getCompanyId();
		if(!po.getMenuId().isEmpty()){
			Wrapper<RoleMenuPO> roleDelete = new EntityWrapper<RoleMenuPO>();
			roleDelete.eq("role_id", po.getRoleId());
			if(menuRoleService.delete(roleDelete)){
				List<RoleMenuPO> polist = new ArrayList<RoleMenuPO>();
				for(String s:po.getMenuId()){
					RoleMenuPO menrole = new RoleMenuPO();
					menrole.setCompanyId(companyId);
					menrole.setRoleId(po.getRoleId());
					menrole.setMenuId(Long.valueOf(s));
					polist.add(menrole);
				}
				menuRoleService.insertBatch(polist);
			}
		}
		return true;
	}

	public Object selectRoleMenuList(RolePO po) {
		// TODO Auto-generated method stub
		JasoUserPO currentUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<RoleMenuPO> wrapper = new EntityWrapper<RoleMenuPO>();
		wrapper.eq("role_id", po.getRoleId())
		.eq("company_id", currentUser.getCompanyId());
		return menuRoleService.selectList(wrapper);
	}

	public Object selectMenuListByRole() {
		// TODO Auto-generated method stub
		JasoUserPO currentUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<UserRolePO> userRoleList = new ArrayList<>();
		Wrapper<UserRolePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", currentUser.getCompanyId())
		.eq("jaso_user_id", currentUser.getJasoUserId())
		.groupBy("role_id");
		userRoleList=userRoleService.selectList(wrapper);
		if(!userRoleList.isEmpty()){
			List<Long> ids = new ArrayList<>();
			for(UserRolePO e:userRoleList){
				ids.add(e.getRoleId());
			}
			MulSelect mul = MulSelect.newInstance("${1}.menuName,${1}.menuPath", new RoleMenuPO(),new MenuPO());
			mul.where("${role_menu}")
				.eq("companyId", currentUser.getCompanyId())
				.in("roleId", ids);
			mul.setGroupBy("rolemenu.menu_id");
	        return serviceMain.mulSelect(mul);
		}
		return null;
	}
}
