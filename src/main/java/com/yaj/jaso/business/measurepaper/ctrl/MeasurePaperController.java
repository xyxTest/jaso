package com.yaj.jaso.business.measurepaper.ctrl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.measurepaper.entity.po.MeasurePaperPO;
import com.yaj.jaso.business.measurepaper.entity.vo.MeasurePaperVo;
import com.yaj.jaso.business.measurepaper.entity.vo.MeasurePointInfo;
import com.yaj.jaso.business.measurepaper.service.MeasurePaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="实测实量-图纸Controller",tags="实测实量-图纸")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MeasurePaper")
public class MeasurePaperController {
	@Autowired
	private MeasurePaperService ts;
	
	/**
	 *实测实量-图纸添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-图纸添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMeasurePaper(
    		@RequestBody MeasurePaperPO po) {
        return ts.add(po);
    }
	/**
	 *实测实量-图纸删除
	 * 
	 **/
	@ApiOperation(value="实测实量-图纸删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMeasurePaper(
    		@RequestBody List<MeasurePaperPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *实测实量-图纸列表获取
	 * 
	 **/
	@ApiOperation(value="实测实量-图纸列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMeasurePaperList(@RequestBody MeasurePaperVo po) {
        return ts.selectListByPage(po);
    }
	/**
	 *项目所有户型图纸获取 
	 * 
	 */
	@ApiOperation(value="项目所有户型图纸获取 接口",notes="项目所有户型图纸获取 ")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object selectList(@RequestBody MeasurePaperPO po) {
        return ts.selectList(po);
    }
	/**
	 *根据户型图纸id获取该图纸的所有测点信息（去除app设置的点信息） 
	 */
	@ApiOperation(value="根据户型图纸id获取该图纸的所有测点信息（去除app设置的点信息）",notes="测点信息获取 ")
	@RequestMapping(value="/getPointList", method = RequestMethod.POST)
    public Object getPointList(@RequestBody MeasurePaperPO po){
        return ts.getPointList(po);
    }
	/**
	 *网页端设置存储测点信息接口 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="网页端设置存储测点信息接口 ",notes="网页端设置存储测点信息接口 ")
	@RequestMapping(value="/pushPoint", method = RequestMethod.POST)
    public Object pushPoint(
    		@RequestBody List<MeasurePointInfo> pointList) {
        return ts.pushPoint(pointList);
    }
}
