package com.yaj.jaso.business.craftsman.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.craftsman.entity.po.CraftsmanPO;
import com.yaj.jaso.business.craftsman.entity.vo.CraftsmanVo;
import com.yaj.jaso.business.craftsman.service.CraftsmanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="项目工匠controller",tags="项目工匠操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Craftsman")
public class CraftsmanController {
	@Autowired
	private CraftsmanService cs;
	
	/**
	 *项目工匠添加 
	 * 
	 **//*
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="项目工匠添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addConstructLog(
    		@RequestBody CraftsmanPO list) {
        return cs.add(list);
    }
	*//**
	 *施工日志删除
	 * 
	 **//*
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="项目工匠删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteConstructLog(
    		@RequestBody List<ConstructLogPO> ConstructLog) {
        return cs.deleteList(ConstructLog);
    }
	*//**
	 *施工日志列表获取
	 * 根据所属班组、日期（筛选条件 起始时间-截止时间）统计整合
	 **//*
	@ApiOperation(value="根据所属班组、日期（筛选条件 起始时间-截止时间）;施工日志列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getConstructLogList(@RequestBody ConstructLogVo constructLog) {
        return cs.selectPageList(constructLog);
    }
	*//**
	 *获取当前用户的工种角色（工人、施工员、班组长、其他） 
	 *//*
	@ApiOperation(value="获取当前用户的工种角色（工人、施工员、班组长、其他）",notes="列表获取")
	@RequestMapping(value="/getRoleType", method = RequestMethod.POST)
    public Object getRoleType() {
        return cs.getRoleType();
    }*/
}
