package com.yaj.jaso.business.worktype.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.worktype.entity.po.WorkTypePO;
import com.yaj.jaso.business.worktype.entity.vo.GetByRoles;
import com.yaj.jaso.business.worktype.entity.vo.WorkTypeVo;
import com.yaj.jaso.business.worktype.service.WorkTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="工种接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="WorkType")
public class WorkTypeController {
	
	@Autowired
	WorkTypeService wts;
	/**
	 * 工种新增/修改接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="工种新增/修改接口",notes="新增/修改")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addWorkType(
			@RequestBody WorkTypePO rmp){
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		rmp.setCompanyId(userInCache.getCompanyId());
		return wts.insertOrUpdate(rmp);
	}
	
	/**
	 * 工种表查询接口(分页)
	 * 
	 **/
	@ApiOperation(value="工种表查询接口（分页）",notes="查询（分页）")
	@RequestMapping(value="/select",method=RequestMethod.POST)
	public Object selectWorkType(
			@RequestBody WorkTypeVo rmp){
		return wts.selectListByPage(rmp);
	}
	
	/**
	 * 工种表所有数据查询接口
	 * 
	 **/
	@ApiOperation(value="工种表所有数据查询接口",notes="查询all")
	@RequestMapping(value="/selectList",method=RequestMethod.POST)
	public Object selectWorkTypeAll(
			@RequestBody WorkTypePO rmp){
		return wts.selectRoleTypeAll(rmp);
	}
	/**
	 * 根据角色查询工种接口
	 * 
	 **/
	@ApiOperation(value="根据角色查询工种接口",notes="根据角色查询工种")
	@RequestMapping(value="/selectByRoleIds",method=RequestMethod.POST)
	public Object selectByRoleIds(
			@RequestBody GetByRoles rmp){
		return wts.selectByRoleIds(rmp);
	}
	/**
	 * 根据用户id查询工种接口
	 * 
	 **/
	@ApiOperation(value="根据用户id查询工种接口",notes="根据用户id查询工种")
	@RequestMapping(value="/selectByUserId",method=RequestMethod.POST)
	public Object selectByUserId(
			@RequestBody JasoUserPO user){
		return wts.selectByUserId(user);
	}
	/**
	 *工种删除接口
	 * 
	 **/
	@ApiOperation(value="工种删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteWorkType(
			@RequestBody List<WorkTypePO> rmp){
		return wts.deleteWorkType(rmp);
	}
	
}
