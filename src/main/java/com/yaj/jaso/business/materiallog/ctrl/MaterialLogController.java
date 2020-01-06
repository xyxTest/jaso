package com.yaj.jaso.business.materiallog.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.materiallog.entity.po.MaterialLogPO;
import com.yaj.jaso.business.materiallog.entity.vo.MaterialLogAdd;
import com.yaj.jaso.business.materiallog.entity.vo.MaterialLogPcAdd;
import com.yaj.jaso.business.materiallog.entity.vo.MaterialLogVo;
import com.yaj.jaso.business.materiallog.service.MaterialLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物资记录controller",tags="物资记录操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MaterialLog")
public class MaterialLogController {
	@Autowired
	private MaterialLogService ms;
	
	/**
	 *app物资记录添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资记录添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMaterialLog(
    		@RequestBody MaterialLogAdd gets) {
        return ms.addList(gets);
    }
	/**
	 *pc物资记录添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资记录添加接口",notes="添加")
	@RequestMapping(value="/addPC", method = RequestMethod.POST)
    public Object addPcMaterialLog(
    		@RequestBody MaterialLogPcAdd gets) {
        return ms.addPcList(gets);
    }
	/**
	 *物资记录删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资分类删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMaterialLog(
    		@RequestBody MaterialLogPO company) {
        return ms.deleteById(company.getMaterialLogId());
    }
	/**
	 *物资记录列表获取
	 * 
	 **/
	@ApiOperation(value="物资记录列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMaterialLogList(@RequestBody MaterialLogVo company) {
		return ms.selectListByPages(company);
    }
	
}
