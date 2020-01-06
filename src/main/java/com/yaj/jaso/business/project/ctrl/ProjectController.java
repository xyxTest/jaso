package com.yaj.jaso.business.project.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.project.entity.vo.CityCode;
import com.yaj.jaso.business.project.entity.vo.ProjectVo;
import com.yaj.jaso.business.project.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="项目表接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Project")
public class ProjectController {
	@Autowired
	ProjectService ps;
	
	/**
	 *项目新增接口 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="项目新增接口",notes="新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addProject(
			@RequestBody ProjectPO ppo){
		return ps.add(ppo);
	}
	/**
	 *项目删除接口 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="项目删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteProject(
			@RequestBody List<ProjectPO> ppo){
		return ps.deleteList(ppo);
	}
	/**
	 *项目修改接口 
	 **/
	@ApiOperation(value="项目修改接口",notes="修改")
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Object updateProject(
			@RequestBody ProjectPO ppo){
		return ps.updateById(ppo);
	}
	
	/**
	 *项目查找接口 
	 **/
	@ApiOperation(value="项目查找接口",notes="查找")
	@RequestMapping(value="/select",method=RequestMethod.POST)
	public Object selectProject(
			@RequestBody ProjectVo ppo){
		return ps.selectProjectList(ppo);
	}
	/**
	 *项目详情查询接口 
	 */
	@ApiOperation(value="项目详情获取接口",notes="详情")
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	public Object selectProjectDetail(
			@RequestBody ProjectPO po){
		return ps.selectProjectDetail(po);
	}
	/**
	 *项目列表获取接口 
	 **/
	@ApiOperation(value="项目数组接口",notes="获取")
	@RequestMapping(value="/selectList",method=RequestMethod.POST)
	public Object selectProjectList(
			@RequestBody ProjectPO po){
		return ps.selectProjectLists(po);
	}
	/**
	 *获取所有城市的citycode 
	 **/
	@ApiOperation(value="项目数组接口",notes="获取")
	@RequestMapping(value="/getCityCode",method=RequestMethod.POST)
	public Object getCityCode(){
		return ps.getCityCode();
	}
	/**
	 *更新城市code 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="项目新增接口",notes="新增")
	@RequestMapping(value="/updateCityCode",method=RequestMethod.POST)
	public Object updateProjectCityCodeInfo(
			@RequestBody List<CityCode> list){
		return ps.updateProjectCityCodeInfo(list);
	}
}
