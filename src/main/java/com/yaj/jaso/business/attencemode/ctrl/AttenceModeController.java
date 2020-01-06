package com.yaj.jaso.business.attencemode.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.attencemode.entity.po.AttenceModePO;
import com.yaj.jaso.business.attencemode.entity.vo.AttenceModeVo;
import com.yaj.jaso.business.attencemode.service.AttenceModeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="考勤模板controller",tags="考勤模板接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="AttenceMode")
public class AttenceModeController {
	@Autowired
	private AttenceModeService aps;
	/**
	 *考勤模板添加 
	 * 
	 **/
	@ApiOperation(value="考勤模板添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addAttenceMode(
    		@RequestBody AttenceModePO attenceMode) {
        return aps.add(attenceMode);
    }
	/**
	 *考勤模板删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="考勤模板删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteAttenceMode(
    		@RequestBody List<AttenceModePO> list) {
        return aps.deleteList(list);
    }
	/**
	 *考勤模板列表获取
	 * 
	 **/
	@ApiOperation(value="考勤模板获取接口",notes="考勤模板获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getAttenceModeList(@RequestBody AttenceModeVo log) {
        return aps.selectPage(log);
    }
}
