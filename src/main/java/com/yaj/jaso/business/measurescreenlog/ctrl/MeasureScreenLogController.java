package com.yaj.jaso.business.measurescreenlog.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.measurescreenlog.entity.po.MeasureScreenLogPO;
import com.yaj.jaso.business.measurescreenlog.service.MeasureScreenLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="实测实量-筛选条件Controller",tags="实测实量-筛选条件")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MeasureScreenLog")
public class MeasureScreenLogController {
	@Autowired
	private MeasureScreenLogService ts;
	
	/**
	 *实测实量-筛选条件添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-筛选条件添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMeasureScreenLog(
    		@RequestBody MeasureScreenLogPO po) {
        return ts.add(po);
    }
	/**
	 *实测实量-筛选条件删除
	 * 
	 **/
	@ApiOperation(value="实测实量-筛选条件删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMeasureScreenLog(
    		@RequestBody List<MeasureScreenLogPO> po) {
        return ts.deleteBatchByIds(po);
    }
	
	/**
	 *实测实量-筛选条件获取 
	 * 
	 */
	@ApiOperation(value="实测实量-筛选条件获取 接口",notes="爆点获取 ")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object selectList(@RequestBody MeasureScreenLogPO po) {
        return ts.selectList(po);
    }
}
