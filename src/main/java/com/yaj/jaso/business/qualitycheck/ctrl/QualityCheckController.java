package com.yaj.jaso.business.qualitycheck.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;
import com.yaj.jaso.business.qualitycheck.entity.vo.AcceptModel;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckAdd;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckSend;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckVo;
import com.yaj.jaso.business.qualitycheck.service.QualityCheckService;
import com.yaj.jaso.business.qualitycheckusers.entity.po.QualityCheckUsersPO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="质量检查单Controller",tags="质量检查单")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="QualityCheck")
public class QualityCheckController {
	@Autowired
	private QualityCheckService ts;
	
	/**
	 *质量检查单添加、重要程度接口、验收接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="质量检查单添加接口、重要程度接口",notes="添加接口")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addQualityCheck(
    		@RequestBody QualityCheckAdd po) {
        return ts.add(po);
    }
	/**
	 * 
	 *质量检查的接受 、设置程度接口
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="质量检查单接受 、设置程度接口",notes="接受 、设置程度接口")
	@RequestMapping(value="/update", method = RequestMethod.POST)
    public Object updateQualityCheck(
    		@RequestBody AcceptModel po) {
        return ts.updateOrAccept(po);
    }
	/**
	 *设置整改进度接口 
	 */
	@ApiOperation(value="质量检查单设置整改进度接口",notes="整改进度设置接口")
	@RequestMapping(value="/setProcess", method = RequestMethod.POST)
    public Object setQualityCheckProcess(
    		@RequestBody QualityCheckPO po) {
        return ts.setProcess(po);
    }
	/**
	 *质量检查单验收接口
	 * 
	 **/
	@ApiOperation(value="验收接口",notes="验收接口")
	@RequestMapping(value="/check", method = RequestMethod.POST)
    public Object checkQualityCheck(
    		@RequestBody QualityCheckPO po) {
        return ts.check(po);
    }
	/**
	 *质量检查单指派
	 * 
	 **/
	@ApiOperation(value="质量检查单指派接口",notes="指派")
	@RequestMapping(value="/send", method = RequestMethod.POST)
    public Object sendQualityCheck(
    		@RequestBody QualityCheckSend po) {
        return ts.send(po);
    }
	/**
	 * 待我整改、待我验收数据获取
	 **/
	@ApiOperation(value="待我整改、待我验收数据获取接口",notes="待我整改、待我验收数据获取")
	@RequestMapping(value="/count", method = RequestMethod.POST)
    public Object countQualityCheck(
    		@RequestBody QualityCheckPO po) {
        return ts.count(po);
    }
	/**
	 *质量检查单删除
	 * 
	 **/
	@ApiOperation(value="质量检查单删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteQualityCheck(
    		@RequestBody List<QualityCheckPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *质量检查单列表获取
	 * 
	 **/
	@ApiOperation(value="质量检查单列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getQualityCheckList(@RequestBody QualityCheckVo po) {
        return ts.selectPageList(po);
    }
	/**
	 *详情 
	 */
	@ApiOperation(value="质量检查单详情获取接口",notes="详情获取")
	@RequestMapping(value="/selectById", method = RequestMethod.POST)
    public Object getQualityCheckDetail(@RequestBody QualityCheckPO po) {
        return ts.getDetail(po);
    }
	/**
	 *质量检查单列表获取
	 * 
	 **/
	@ApiOperation(value="质量检查单列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectByPage", method = RequestMethod.POST)
    public Object getQualityCheckLists(@RequestBody QualityCheckVo po) {
        return ts.selectLists(po);
    }
	/**
	 *新增参与人接口 
	 */
	@ApiOperation(value="新增参与人接口 ",notes="新增参与人接口")
	@RequestMapping(value="/addAboutUserList", method = RequestMethod.POST)
    public Object addAboutUserList(@RequestBody List<QualityCheckUsersPO> po) {
        return ts.addAboutUserList(po);
    }
	/**
	 *奖惩事由 
	 */
	@ApiOperation(value="奖惩事由列表获取",notes="获取奖惩")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getList(@RequestBody QualityCheckPO po) {
        return ts.getList(po);
    }

}
