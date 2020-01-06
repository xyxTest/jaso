package com.yaj.jaso.business.userintegrallog.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.userintegrallog.entity.po.UserIntegralLogPO;
import com.yaj.jaso.business.userintegrallog.service.UserIntegralLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="学习积分Controller",tags="学习积分")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="UserIntegralLog")
public class UserIntegralLogController {
	@Autowired
	private UserIntegralLogService sf;

	/**
	 *签到
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="签到接口",notes="签到")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addUserIntegralLog(
    		@RequestBody UserIntegralLogPO po) {
        return sf.add(po);
    }
	
	/**
	 *学习积分列表获取
	 * 
	 **/
	@ApiOperation(value="学习积分列表获取接口",notes="学习积分列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getUserIntegralLogList() {
        return sf.selectLists();
    }
	
}
