package com.yaj.jaso.business.message.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.message.entity.po.MessagePO;
import com.yaj.jaso.business.message.entity.vo.MessageVo;
import com.yaj.jaso.business.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="消息接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Message")
public class MessageController {
	@Autowired
	MessageService ms;
	
	
	/**
	 *消息删除接口 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="消息删除接口",notes="删除")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteMessage(
			@RequestBody List<MessagePO> mpo){
		return ms.deleteList(mpo);
	}
	
	/**
	 * 
	 *消息设置已读接口 
	 **/
	@ApiOperation(value="消息设置已读接口",notes="修改")
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Object updateMessage(
			@RequestBody MessagePO mpo){
		return ms.updateById(mpo);
	}
	
	/**
	 *消息列表查询接口 
	 **/
	@ApiOperation(value="消息列表查询接口",notes="查询")
	@RequestMapping(value="/select",method=RequestMethod.POST)
	public Object selectMessage(
			@RequestBody MessageVo mpo){
		return ms.selectList(mpo);
	}
	/**
	 *消息未读个数获取 
	 */
	@ApiOperation(value="消息角标获取接口",notes="角标获取")
	@RequestMapping(value="/getCount",method=RequestMethod.POST)
	public Object getCount(){
		return ms.getCount();
	}
	
}
