package com.yaj.jaso.business.measuresite.ctrl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.measuresite.entity.po.MeasureSitePO;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureSiteVo;
import com.yaj.jaso.business.measuresite.service.MeasureSiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="实测实量-区域Controller",tags="实测实量-区域")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MeasureSite")
public class MeasureSiteController {
	@Autowired
	private MeasureSiteService ts;
	
	/**
	 *实测实量-区域楼栋获取
	 * 
	 **/
	@ApiOperation(value="实测实量-楼栋列表获取接口",notes="楼栋列表获取")
	@RequestMapping(value="/getBuildingInfo", method = RequestMethod.POST)
    public Object getBuildingInfo(
    		@RequestBody MeasureSitePO po) {
        return ts.getBuildingInfo(po);
    }
	
	/**
	 *实测实量-区域添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-区域添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMeasureSite(
    		@RequestBody List<MeasureSitePO> po) {
        return ts.add(po);
    }
	/**
	 *楼栋同步 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-同步项目楼栋数据接口",notes="同步项目楼栋")
	@RequestMapping(value="/tongbu", method = RequestMethod.POST)
    public Object initMeasureSite(
    		@RequestBody MeasureSitePO po) {
        return ts.initMeasureSite(po);
    }
	/**
	 *实测实量-区域删除
	 * 
	 **/
	@ApiOperation(value="实测实量-区域删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMeasureSite(
    		@RequestBody List<MeasureSitePO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *实测实量-区域列表获取
	 * 
	 **/
	@ApiOperation(value="实测实量-区域列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMeasureSiteList(@RequestBody MeasureSiteVo po) {
        return ts.selectListByPage(po);
    }
	/**
	 *实测实量-区域list获取
	 * 
	 **/
	@ApiOperation(value="实测实量-区域list获取接口",notes="list获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getMeasureSiteLists(@RequestBody MeasureSitePO po) {
        return ts.selectLists(po);
    }
	/**
	 * 获取所有区域的所有图纸
	 */
	@ApiOperation(value="所有区的所有图纸获取接口",notes="图纸list获取")
	@RequestMapping(value="/selectPapersList", method = RequestMethod.POST)
    public Object selectPapersList(@RequestBody MeasureSitePO po) {
        return ts.selectPapersList(po);
    }
	/*获取当前节点的所有子节点*/
	@ApiOperation(value="获取当前节点的所有子节点",notes="获取当前节点的所有子节点")
	@RequestMapping(value="/selectListByPid", method = RequestMethod.POST)
    public Object selectListByPid(@RequestBody MeasureSitePO company) {
        return ts.selectListByPid(company);
    }
	/*获取楼栋下面的所有房间号*/
	@ApiOperation(value="获取楼栋下面的所有房间号",notes="获取楼栋下面的所有房间号")
	@RequestMapping(value="/selectListByPids", method = RequestMethod.POST)
    public Object selectListByPids(@RequestBody MeasureSiteVo company) {
        return ts.selectListByPids(company);
    }
	/*app缓存数据拿取*/
	@ApiOperation(value="app缓存数据拿取",notes="获取缓存数据")
	@RequestMapping(value="/getCacheData", method = RequestMethod.POST)
    public Object getCacheData(@RequestBody MeasureSitePO company) {
        return ts.getCacheData(company);
    }
	/**
	 *实测实量-区域编辑名称、户型图纸 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-区域编辑名称、户型图纸接口",notes="编辑")
	@RequestMapping(value="/update", method = RequestMethod.POST)
    public Object updateMeasureSite(
    		@RequestBody MeasureSitePO po) {
        return ts.update(po);
    }
	/**
	 * 获取实测实量所有的户型
	 **/
	@ApiOperation(value="获取实测实量所有的户型接口",notes="获取实测实量所有的户型")
	@RequestMapping(value="/getAllApartment", method = RequestMethod.POST)
    public Object getAllApartment(
    		@RequestBody MeasureSitePO po) {
        return ts.getAllApartment(po);
    }
}
