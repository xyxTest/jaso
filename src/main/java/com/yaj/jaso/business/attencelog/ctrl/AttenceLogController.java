package com.yaj.jaso.business.attencelog.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.attencelog.entity.po.AttenceLogPO;
import com.yaj.jaso.business.attencelog.entity.vo.AttenceCount;
import com.yaj.jaso.business.attencelog.entity.vo.AttenceImport;
import com.yaj.jaso.business.attencelog.entity.vo.AttenceLogVo;
import com.yaj.jaso.business.attencelog.service.AttenceLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="考勤打卡记录controller",tags="打卡记录接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="AttenceLog")
public class AttenceLogController {
	@Autowired
	private AttenceLogService aps;
	/**
	 *考勤打卡记录添加 
	 * 
	 **/
	@ApiOperation(value="考勤打卡记录添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addAttenceLog(
    		@RequestBody AttenceImport attencePlace) {
        return aps.add(attencePlace);
    }
	/**
	 *考勤打卡记录删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="考勤打卡记录删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteAttenceLog(
    		@RequestBody List<AttenceLogPO> list) {
        return aps.deleteList(list);
    }
	/**
	 *考勤打卡记录列表获取
	 * 
	 **/
	@ApiOperation(value="考勤打卡记录列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getAttenceLogList(@RequestBody AttenceLogVo log) {
        return aps.selectPage(log);
    }
   /**
    *考勤每月统计 
    * 
    */
	@ApiOperation(value="考勤记录按月统计接口",notes="考勤统计")
	@RequestMapping(value="/countNum",method = RequestMethod.POST)
	public Object getCountNum(@RequestBody AttenceCount count){
		return aps.getCountNum(count);
	}
    
}
