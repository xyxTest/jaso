package com.yaj.jaso.business.measurepoint.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.measurepoint.entity.vo.MeasurePointAdd;
import com.yaj.jaso.business.measurepoint.entity.vo.MeasurePointAddApp;
import com.yaj.jaso.business.measurepoint.service.MeasurePointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="实测实量-测点Controller",tags="实测实量-测点")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MeasurePoint")
public class MeasurePointController {
	@Autowired
	private MeasurePointService ts;
	
	/**
	 *实测实量-web测点添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-web测点添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addWebMeasurePoint(
    		@RequestBody MeasurePointAdd po) {
        return ts.add(po);
    }
	/**
	 *实测实量-app测点添加 
	 * 
	 **/
	@ApiOperation(value="实测实量-app测点添加接口",notes="添加")
	@RequestMapping(value="/appAdd", method = RequestMethod.POST)
    public Object addAppMeasurePoint(
    		@RequestBody List<MeasurePointAddApp> po) {
		
        return ts.appAdd(po);
    }
	/**
	 *实测实量-测点删除
	 * 
	 **/
	@ApiOperation(value="实测实量-测点删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMeasurePoint(
    		@RequestBody List<MeasurePointPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *实测实量-区域列表获取
	 * 
	 **/
	@ApiOperation(value="实测实量-区域列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMeasurePointList(@RequestBody MeasurePointPO po) {
        return ts.selectListById(po);
    }
	/*
	 * app获取所有测点-检查项-房间关系数据
	 */
	@ApiOperation(value="app获取所有测点-检查项-房间关系数据",notes="获取")
	@RequestMapping(value="/selectAppAll", method = RequestMethod.POST)
    public Object selectAppAll(@RequestBody MeasurePointPO po) {
        return ts.selectAppAll(po);
    }
	
}
