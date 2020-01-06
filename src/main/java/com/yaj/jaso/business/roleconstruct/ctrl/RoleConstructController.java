package com.yaj.jaso.business.roleconstruct.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yaj.jaso.business.roleconstruct.entity.po.RoleConstructPO;
import com.yaj.jaso.business.roleconstruct.entity.vo.RoleConstructVo;
import com.yaj.jaso.business.roleconstruct.service.RoleConstructService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="角色-施工内容关系表controller",tags="组织架构-施工内容关系表操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="RoleConstruct")
public class RoleConstructController {
	@Autowired
	private RoleConstructService cs;
	
	/**
	 *用户角色-施工内容关系表添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="用户角色-施工内容关系表添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addRoleConstruct(
    		@RequestBody RoleConstructPO RoleConstruct) {
        return cs.add(RoleConstruct);
    }
	/**
	 *用户角色-施工内容关系表删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="用户角色-施工内容关系表删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteRoleConstruct(
    		@RequestBody List<RoleConstructPO> roleConstruct) {
        return cs.deleteList(roleConstruct);
    }
	/**
	 *用户角色-施工内容关系表列表获取
	 * 
	 **/
	@ApiOperation(value="用户角色-施工内容关系表列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getRoleConstructList(@RequestBody RoleConstructVo roleConstruct) {
        return cs.selectPageList(roleConstruct);
    }
	/**
	 *用户角色-施工内容关系表列表获取
	 * 
	 **/
	@ApiOperation(value="用户角色-施工内容关系表列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getRoleConstructLists(@RequestBody RoleConstructVo roleConstruct) {
        return cs.selectPageLists(roleConstruct);
    }
}
