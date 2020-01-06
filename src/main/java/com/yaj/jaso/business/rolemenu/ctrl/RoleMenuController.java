package com.yaj.jaso.business.rolemenu.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yaj.jaso.business.rolemenu.entity.po.RoleMenuPO;
import com.yaj.jaso.business.rolemenu.entity.vo.RoleMenuVo;
import com.yaj.jaso.business.rolemenu.service.RoleMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="菜单角色关系表接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="RoleMenu")
public class RoleMenuController {
	@Autowired
	RoleMenuService rms;
	
	/**
	 * 菜单角色关系表新增接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="菜单角色关系表新增接口",notes="新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addRoleMenu(
			@RequestBody RoleMenuPO rmp){
		return rms.insert(rmp);
	}
	/**
	 * 菜单角色关系表删除接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="菜单角色关系表删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteRoleMenu(
			@RequestBody RoleMenuPO rmp){
		return rms.deleteById(rmp.getRoleMenuId());
	}
	/**
	 * 菜单角色关系表修改接口
	 * 
	 **/
	@ApiOperation(value="菜单角色关系表修改接口",notes="修改")
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Object updateRoleMenu(
			@RequestBody RoleMenuPO rmp){
		return rms.updateById(rmp);
	}
	/**
	 * 菜单角色关系表查询接口
	 * 
	 **/
	@ApiOperation(value="菜单角色关系表查询接口",notes="查询")
	@RequestMapping(value="/select",method=RequestMethod.POST)
	public Object selectRoleMenu(
			@RequestBody RoleMenuVo rmp){
		return rms.selectLists(rmp);
	}
	
}
