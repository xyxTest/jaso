package com.yaj.jaso.business.menu.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yaj.jaso.business.menu.entity.po.MenuPO;
import com.yaj.jaso.business.menu.entity.vo.MenuVo;
import com.yaj.jaso.business.menu.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="菜单表接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Menu")
public class MenuController {
	@Autowired
	MenuService ms;
	/**
	 *菜单新增接口 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="菜单新增接口",notes="新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addMenu(
			@RequestBody MenuPO mpo){
		return ms.add(mpo);
	}
	
	/**
	 *菜单删除接口 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="菜单删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteMenu(
			@RequestBody List<MenuPO> mpo){
		return ms.deleteList(mpo);
	}
	
	/**
	 * 
	 *菜单修改接口 
	 **/
	@ApiOperation(value="菜单修改接口",notes="修改")
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Object updateMenu(
			@RequestBody MenuPO mpo){
		return ms.updateById(mpo);
	}
	
	/**
	 *菜单查询接口 
	 **/
	@ApiOperation(value="菜单查询接口",notes="查询")
	@RequestMapping(value="/select",method=RequestMethod.POST)
	public Object selectMenu(
			@RequestBody MenuVo mpo){
		return ms.selectListByOptions(mpo);
	}
	
	/**
	 *菜单列表获取接口 
	 **/
	@ApiOperation(value="根据当前用户获取菜单列表",notes="获取")
	@RequestMapping(value="/selectList",method=RequestMethod.POST)
	public Object selectMenuList(){
		return ms.selectListByRole();
	}
	/**
	 *所有菜单获取接口 
	 **/
	@ApiOperation(value="所有菜单获取",notes="获取")
	@RequestMapping(value="/selectAllList",method=RequestMethod.POST)
	public Object selectAllMenuList(){
		return ms.selectAllMenuList();
	}
	/**
	 *菜单列表获取接口 
	 **/
	@ApiOperation(value="根据当前用户获取菜单列表",notes="获取")
	@RequestMapping(value="/selectTree",method=RequestMethod.POST)
	public Object selectMenuTreeList(){
		return ms.selectTreeByRole();
	}
}
