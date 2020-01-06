package com.yaj.jaso.business.securitycheck.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.securitycheck.entity.vo.AcceptModel;
import com.yaj.jaso.business.qualitycheckusers.entity.po.QualityCheckUsersPO;
import com.yaj.jaso.business.securitycheck.entity.po.SecurityCheckPO;
import com.yaj.jaso.business.securitycheck.entity.vo.SecurityCheckAdd;
import com.yaj.jaso.business.securitycheck.entity.vo.SecurityCheckSend;
import com.yaj.jaso.business.securitycheck.entity.vo.SecurityCheckVo;
import com.yaj.jaso.business.securitycheck.service.SecurityCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="安全检查单Controller",tags="安全检查单")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="SecurityCheck")
public class SecurityCheckController {
	@Autowired
	private SecurityCheckService ts;
	
	/**
	 *安全检查单添加、重要程度接口、验收接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="安全检查单添加接口",notes="添加接口")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addSecurityCheck(
    		@RequestBody SecurityCheckAdd po) {
        return ts.add(po);
    }
	/**
	 * 
	 *质量检查的接受 、设置程度接口
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="质量检查单接受 、设置程度接口",notes="接受 、设置程度接口")
	@RequestMapping(value="/update", method = RequestMethod.POST)
    public Object updateSecurityCheck(
    		@RequestBody AcceptModel po) {
        return ts.updateOrAccept(po);
    }
	/**
	 *设置整改进度接口 
	 */
	@ApiOperation(value="质量检查单设置整改进度接口",notes="整改进度设置接口")
	@RequestMapping(value="/setProcess", method = RequestMethod.POST)
    public Object setSecurityCheckProcess(
    		@RequestBody SecurityCheckPO po) {
        return ts.setProcess(po);
    }
	/**
	 *安全检查单验收接口
	 * 
	 **/
	@ApiOperation(value="验收接口",notes="验收接口")
	@RequestMapping(value="/check", method = RequestMethod.POST)
    public Object checkSecurityCheck(
    		@RequestBody SecurityCheckPO po) {
        return ts.check(po);
    }
	/**
	 *安全检查单指派
	 * 
	 **/
	@ApiOperation(value="安全检查单指派接口",notes="指派")
	@RequestMapping(value="/send", method = RequestMethod.POST)
    public Object sendSecurityCheck(
    		@RequestBody SecurityCheckSend po) {
        return ts.send(po);
    }
	/**
	 *安全检查单删除
	 * 
	 **/
	@ApiOperation(value="安全检查单删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteSecurityCheck(
    		@RequestBody List<SecurityCheckPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *安全检查单列表获取
	 * 
	 **/
	@ApiOperation(value="安全检查单列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getSecurityCheckList(@RequestBody SecurityCheckVo po) {
        return ts.selectPageList(po);
    }
	/**
	 *详情 
	 */
	@ApiOperation(value="安全检查单详情获取接口",notes="详情获取")
	@RequestMapping(value="/selectById", method = RequestMethod.POST)
    public Object getSecurityCheckDetail(@RequestBody SecurityCheckPO po) {
        return ts.getDetail(po);
    }
	/**
	 * 待我整改、待我验收数据获取
	 **/
	@ApiOperation(value="待我整改、待我验收数据获取接口",notes="待我整改、待我验收数据获取")
	@RequestMapping(value="/count", method = RequestMethod.POST)
    public Object countSecurityCheck(
    		@RequestBody SecurityCheckPO po) {
        return ts.count(po);
    }
	/**
	 *安全检查单列表获取
	 * 
	 **/
	@ApiOperation(value="安全检查单列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getSecurityCheckLists(@RequestBody SecurityCheckPO po) {
        return ts.selectLists(po);
    }
	/**
	 *质量检查单列表获取
	 * 
	 **/
	@ApiOperation(value="质量检查单列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectByPage", method = RequestMethod.POST)
    public Object getSecurityCheckByPage(@RequestBody SecurityCheckVo po) {
        return ts.selectByPage(po);
    }
	/**
	 *新增参与人接口 
	 */
	@ApiOperation(value="新增参与人接口 ",notes="新增参与人接口")
	@RequestMapping(value="/addAboutUserList", method = RequestMethod.POST)
    public Object addAboutUserList(@RequestBody List<QualityCheckUsersPO> po) {
        return ts.addAboutUserList(po);
    }

}
