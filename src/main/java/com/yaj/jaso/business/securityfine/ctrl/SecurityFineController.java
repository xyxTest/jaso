package com.yaj.jaso.business.securityfine.ctrl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.securityfine.entity.po.SecurityFinePO;
import com.yaj.jaso.business.securityfine.entity.vo.SecurityFineAdd;
import com.yaj.jaso.business.securityfine.entity.vo.SecurityFineVo;
import com.yaj.jaso.business.securityfine.service.SecurityFineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="安全奖惩单controller",tags="安全奖惩单操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="SecurityFine")
public class SecurityFineController {
	@Autowired
	private SecurityFineService cs;
	
	/**
	 *安全奖惩单添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="安全奖惩单添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addSecurityFine(
    		@RequestBody SecurityFineAdd company) {
		
        return cs.add(company);
    }
	/**
	 *安全奖惩单删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="安全奖惩单删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteSecurityFine(
    		@RequestBody List<SecurityFinePO> company) {
        return cs.deleteBatchByIds(company);
    }
	/**
	 *安全整改单列表获取
	 * 
	 **/
	@ApiOperation(value="安全奖惩单列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getSecurityFineList(@RequestBody SecurityFineVo pt) {
        return cs.selectPageList(pt);
    }
	/**
	 * 详情获取相关单位或者人员接口
	 */
	@ApiOperation(value="详情获取相关单位或者人员接口",notes="详情获取相关单位或者人员接口")
	@RequestMapping(value="/selectById", method = RequestMethod.POST)
    public Object selectById(@RequestBody SecurityFinePO po) {
        return cs.selectById(po);
    }
	
}
