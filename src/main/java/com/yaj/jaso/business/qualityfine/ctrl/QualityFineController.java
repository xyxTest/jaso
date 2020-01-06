package com.yaj.jaso.business.qualityfine.ctrl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.qualityfine.entity.po.QualityFinePO;
import com.yaj.jaso.business.qualityfine.entity.vo.QualityFineAdd;
import com.yaj.jaso.business.qualityfine.entity.vo.QualityFineVo;
import com.yaj.jaso.business.qualityfine.service.QualityFineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="质量奖惩单controller",tags="质量整改单操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="QualityFine")
public class QualityFineController {
	@Autowired
	private QualityFineService cs;
	
	/**
	 *质量奖惩单添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="质量奖惩单添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addQualityFine(
    		@RequestBody QualityFineAdd company) {
		
        return cs.add(company);
    }
	/**
	 *质量奖惩单删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="质量奖惩单删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteQualityFine(
    		@RequestBody List<QualityFinePO> company) {
        return cs.deleteBatchByIds(company);
    }
	/**
	 *质量奖惩单列表获取
	 * 
	 **/
	@ApiOperation(value="质量奖惩单列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getQualityFineList(@RequestBody QualityFineVo pt) {
        return cs.selectPageList(pt);
    }
	/**
	 * 详情获取相关单位或者人员接口
	 */
	@ApiOperation(value="详情获取相关单位或者人员接口",notes="详情获取相关单位或者人员接口")
	@RequestMapping(value="/selectById", method = RequestMethod.POST)
    public Object selectById(@RequestBody QualityFinePO po) {
        return cs.selectById(po);
    }
	
}
