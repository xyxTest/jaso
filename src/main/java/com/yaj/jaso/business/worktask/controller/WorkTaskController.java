package com.yaj.jaso.business.worktask.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.worktask.entity.po.WorkTaskPO;
import com.yaj.jaso.business.worktask.entity.vo.WorkAllOptions;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskAdd;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskCheck;
import com.yaj.jaso.business.worktask.entity.vo.WorkTaskVo;
import com.yaj.jaso.business.worktask.service.WorkTaskService;
import com.yaj.jaso.business.worktaskuser.entity.po.WorkTaskUserPO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="工作任务Controller",tags="工作任务")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="WorkTask")
public class WorkTaskController {
	@Autowired
	private WorkTaskService wts;
	/**
	 *工作任务添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="工作任务添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addWorkTask(
    		@RequestBody WorkTaskAdd po) {
        return wts.add(po);
    }
	/**
	 *设置整改进度接口 
	 */
	@ApiOperation(value="工作任务设置整改进度接口",notes="整改进度设置接口")
	@RequestMapping(value="/setProcess", method = RequestMethod.POST)
    public Object setWorkTaskProcess(
    		@RequestBody WorkTaskPO po) {
        return wts.setProcess(po);
    }
	/**
	 *工作任务接受拒绝接口
	 * 
	 **/
	@ApiOperation(value="接受或拒绝接口",notes="接受或拒绝接口")
	@RequestMapping(value="/accept", method = RequestMethod.POST)
    public Object acceptWorkTask(
    		@RequestBody WorkTaskCheck po) {
        return wts.acceptWorkTask(po);
    }
	/**
	 *工作任务验收接口
	 * 
	 **/
	@ApiOperation(value="验收接口",notes="验收接口")
	@RequestMapping(value="/check", method = RequestMethod.POST)
    public Object checkWorkTask(
    		@RequestBody WorkTaskCheck po) {
        return wts.check(po);
    }
	/**
	 *工作任务删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="工作任务删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteWorkTask(
    		@RequestBody List<WorkTaskPO> po) {
        return wts.deleteBatchByIds(po);
    }
	/**
	 *个人板块工作任务列表获取
	 * 
	 **/
	@ApiOperation(value="个人板块工作任务列表获取接口",notes="个人板块工作任务列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object selectWorkTaskList(
    		@RequestBody WorkTaskVo po) {
        return wts.getList(po);
    }
	/**
	 *个人板块工作任务列表获取
	 * 
	 **/
	@ApiOperation(value="个人板块工作任务列表获取接口",notes="个人板块工作任务列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object selectWorkTask(
    		@RequestBody WorkAllOptions po) {
        return wts.getAllMyList(po);
    }
	/**
	 *详情 
	 */
	@ApiOperation(value="工作任务详情获取接口",notes="详情获取")
	@RequestMapping(value="/selectById", method = RequestMethod.POST)
    public Object getWorkTaskDetail(@RequestBody WorkTaskPO po) {
        return wts.getDetail(po);
    }
	/**
	 *新增参与人接口 
	 */
	@ApiOperation(value="新增参与人接口 ",notes="新增参与人接口")
	@RequestMapping(value="/addAboutUserList", method = RequestMethod.POST)
    public Object addAboutUserList(@RequestBody List<WorkTaskUserPO> po) {
        return wts.addAboutUserList(po);
    }
}
