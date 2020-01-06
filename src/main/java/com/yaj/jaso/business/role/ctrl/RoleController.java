package com.yaj.jaso.business.role.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.role.entity.po.RolePO;
import com.yaj.jaso.business.role.entity.vo.RoleSetting;
import com.yaj.jaso.business.role.entity.vo.RoleVo;
import com.yaj.jaso.business.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="角色表接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Role")
public class RoleController {
	@Autowired
	RoleService rs;
	/**
	 *角色新增接口 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="角色（新增/更新） 接口",notes="新增、更新")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addRole(
			@RequestBody RolePO rpo){
		return rs.add(rpo);
	}
	/**
	 *角色删除接口 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="角色删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteRole(
			@RequestBody List<RolePO> rpo){
		return rs.deleteList(rpo);
	}
	/**
	 *角色修改接口 
	 **/
	@ApiOperation(value="角色修改接口",notes="修改")
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Object updateRole(
			@RequestBody RolePO rpo){
		return rs.updateById(rpo);
	}
	/**
	 *角色列表分页查询接口 
	 **/
	@ApiOperation(value="角色列表获取接口",notes="列表获取")
	@RequestMapping(value="/select",method=RequestMethod.POST)
	public Object selectRole(
			@RequestBody RoleVo rpo){
		return rs.selectList(rpo);
	}
	/**
	 *角色列表查询接口 
	 **/
	@ApiOperation(value="根据当前用户权限获取角色列表",notes="列表获取")
	@RequestMapping(value="/selectList",method=RequestMethod.POST)
	public Object selectRoleList(
			@RequestBody RolePO po){
		return rs.selectRoleList(po);
	}
	/**
	 *角色分配权限 
	 * 
	 */
	@ApiOperation(value="为当前用户权限分配角色",notes="分配角色")
	@RequestMapping(value="/roleSet",method=RequestMethod.POST)
	public Object roleSet(
			@RequestBody RoleSetting po){
		return rs.roleSet(po);
	}
	/**
	 *角色权限详情
	 * 
	 */
	@ApiOperation(value="角色权限详情",notes="角色权限详情")
	@RequestMapping(value="/selectRoleMenuList",method=RequestMethod.POST)
	public Object selectRoleMenuList(
			@RequestBody RolePO po){
		return rs.selectRoleMenuList(po);
	}
	/**
	 *根据当前用户权限查询所有能看到的菜单列表
	 * 
	 */
	@ApiOperation(value="根据当前用户权限查询所有能看到的菜单列表",notes="根据当前用户权限查询所有能看到的菜单列表")
	@RequestMapping(value="/selectMenuListByRole",method=RequestMethod.POST)
	public Object selectMenuListByRole(){
		return rs.selectMenuListByRole();
	}
}
