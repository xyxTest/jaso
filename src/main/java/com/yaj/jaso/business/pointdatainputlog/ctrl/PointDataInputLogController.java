package com.yaj.jaso.business.pointdatainputlog.ctrl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.pointdatainputlog.entity.po.PointDataInputLogPO;
import com.yaj.jaso.business.pointdatainputlog.service.PointDataInputLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="实测实量-测点录入数据Controller",tags="实测实量-测点录入数据")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="PointDataInputLog")
public class PointDataInputLogController {
	@Autowired
	private PointDataInputLogService ts;
	
	/**
	 *实测实量-测点录入数据添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-测点录入数据添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addPointDataInputLog(
    		@RequestBody List<PointDataInputLogPO> po) {
		
        return ts.add(po);
    }
	/**
	 *实测实量-测点录入数据删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-测点录入数据删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deletePointDataInputLog(
    		@RequestBody List<PointDataInputLogPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *实测实量-区域列表获取
	 * 
	 **/
	@ApiOperation(value="实测实量-测点录入数据列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getPointDataInputLogList(@RequestBody PointDataInputLogPO po) {
        return ts.selectLists(po);
    }
}
