package com.yaj.jaso.business.department.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yaj.jaso.business.department.entity.po.DepartmentPO;
import com.yaj.jaso.business.department.entity.vo.DepartmentVo;
import com.yaj.jaso.business.department.mapper.DepartmentMapper;
import com.yaj.jaso.business.department.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="组织架构表接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Department")
public class DepartmentController {
	@Autowired
	DepartmentService ds;
	@Resource
	DepartmentMapper dm;
	/**
	 * 项目架构增加
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="组织架构新增接口",notes="组织架构新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addDepartment(
			@RequestBody DepartmentPO dpo){
		return ds.add(dpo);
	}
	/**
	 * 项目架构增加
	 **/
	@ApiOperation(value="组织架构新增接口",notes="组织架构新增")
	@RequestMapping(value="/adds",method=RequestMethod.POST)
	public Object addDepartments(
			@RequestBody DepartmentPO dpo){
		return ds.adds(dpo);
	}
	/**
	 *项目架构删除接口
	 *
	 **/
	@ApiOperation(value="组织架构删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteDepartment(
			@RequestBody List<DepartmentPO> dpo){
		return ds.deleteDepartmentList(dpo);
	}
	/**
	 *项目组织架构修改接口 
	 * 
	 */
	@ApiOperation(value="组织架构修改接口",notes="修改")
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Object updateDepartment(
			@RequestBody DepartmentPO dpo){
		return ds.updateById(dpo);
	}
	/**
	 *查询接口 
	 */
	@ApiOperation(value="组织架构查询接口 ",notes="查询")
	@RequestMapping(value="selectByPid",method=RequestMethod.POST)
	public Object selectDepartmentByPid(
			@RequestBody DepartmentVo dpo){
		return ds.selectLists(dpo);
	}
	
	/**
	 *查询所有数据接口 
	 */
	@ApiOperation(value="组织架构查询所有数据接口 ",notes="查询")
	@RequestMapping(value="select",method=RequestMethod.POST)
	public Object selectDepartment(
			@RequestBody DepartmentPO dpo){
		return ds.selectListByCompanyId(dpo);
	}
	/*
	 * 查询返回结构树
	 * */
	@ApiOperation(value="组织架构tree查询 ",notes="查询")
	@RequestMapping(value="selectTree",method=RequestMethod.POST)
	public Object selectTreeLists(){
		return ds.selectTreeLists();
	}
	/*
	 * 查询返回结构树
	 * */
	@ApiOperation(value="组织架构project_tree查询 ",notes="查询")
	@RequestMapping(value="selectProjectTree",method=RequestMethod.POST)
	public Object selectProjectTree(){
		return ds.selectProjectTree();
	}
	/**
	 *获取项目部门信息及部门下面的一级部门信息 
	 */
	@ApiOperation(value="获取项目部门信息及部门下面的一级部门信息  ",notes="查询")
	@RequestMapping(value="selectDepartmentByProjectId",method=RequestMethod.POST)
	public Object selectProjectByProjectId(@RequestBody DepartmentPO dpo){
		return ds.selectProjectByProjectId(dpo);
	}
	
}
