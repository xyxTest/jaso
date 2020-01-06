package com.yaj.jaso.business.studyevent.ctrl;

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
import com.yaj.jaso.business.studyevent.entity.po.StudyEventPO;
import com.yaj.jaso.business.studyevent.entity.vo.StudyEventVo;
import com.yaj.jaso.business.studyevent.service.StudyEventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="学习事件接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyEvent")
public class StudyEventController {
	@Autowired
	StudyEventService studyEventService;
	/**
	 * 学习事件新增接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习事件新增接口",notes="新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addStudyEvent(
			@RequestBody StudyEventPO rmp){
		JasoUserPO user = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		rmp.setCompanyId(user.getCompanyId());
		return studyEventService.insertOrUpdate(rmp);
	}
	/**
	 * 学习事件删除接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习事件删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteStudyEvent(
			@RequestBody List<StudyEventPO> rmps){
		return studyEventService.deleteByIds(rmps);
	}
	/**
	 * 学习事件修改接口
	 * 
	 **/
	@ApiOperation(value="学习事件修改接口",notes="修改")
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Object updateStudyEvent(
			@RequestBody StudyEventPO rmp){
		return studyEventService.updateById(rmp);
	}
	/**
	 * 学习事件查询接口
	 * 
	 **/
	@ApiOperation(value="学习事件查询接口",notes="查询")
	@RequestMapping(value="/select",method=RequestMethod.POST)
	public Object selectStudyEvent(
			@RequestBody StudyEventVo rmp){
		return studyEventService.selectLists(rmp);
	}
}
