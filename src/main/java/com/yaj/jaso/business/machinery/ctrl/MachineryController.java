package com.yaj.jaso.business.machinery.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.machinery.entity.po.MachineryPO;
import com.yaj.jaso.business.machinery.entity.vo.MachineryVo;
import com.yaj.jaso.business.machinery.service.MachineryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="机械controller",tags="机械操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Machinery")
public class MachineryController {
	@Autowired
	private MachineryService cs;
	
	/**
	 *机械添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="机械添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMachinery(
    		@RequestBody MachineryPO Machinery) {
		
        return cs.add(Machinery);
    }
	/**
	 *机械删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="机械删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMachinery(
    		@RequestBody List<MachineryPO> Machinery) {
        return cs.deleteLists(Machinery);
    }
	/**
	 *机械列表获取
	 * 
	 **/
	@ApiOperation(value="机械列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMachineryList(@RequestBody MachineryVo Machinery) {
        return cs.selectPageList(Machinery);
    }
	
	/**
	 *all机械列表获取
	 * 
	 **/
	@ApiOperation(value="all机械获取接口",notes="all获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getMachineryLists(@RequestBody MachineryPO Machinery) {
        return cs.selectMachineryList(Machinery);
    }
}
