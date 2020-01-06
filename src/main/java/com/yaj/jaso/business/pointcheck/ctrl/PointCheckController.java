package com.yaj.jaso.business.pointcheck.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;
import com.yaj.jaso.business.pointcheck.service.PointCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="实测实量-测点-检查项关系Controller",tags="实测实量-测点-检查项")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="PointCheck")
public class PointCheckController {
	@Autowired
	private PointCheckService ts;
	
	/**
	 *实测实量-测点-检查项添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-测点-检查项添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addPointCheck(
    		@RequestBody PointCheckPO po) {
		
        return ts.add(po);
    }
	/**
	 *实测实量-测点-检查项删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-测点-检查项删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deletePointCheck(
    		@RequestBody List<PointCheckPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *实测实量-区域列表获取
	 * 
	 **/
	@ApiOperation(value="实测实量-测点-检查项列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getPointCheckList(@RequestBody PointCheckPO po) {
        return ts.selectLists(po);
    }
}
