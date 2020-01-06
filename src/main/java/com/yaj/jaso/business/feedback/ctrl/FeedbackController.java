package com.yaj.jaso.business.feedback.ctrl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.feedback.entity.po.FeedbackPO;
import com.yaj.jaso.business.feedback.entity.vo.FeedbackVo;
import com.yaj.jaso.business.feedback.service.FeedbackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="意见反馈controller",tags="意见反馈操作接口")
@EnableAutoConfiguration//借助@import的帮助，将所有符合自动配置条件的Bean定义加载到IOC容器里
@RestController
@RequestMapping(value="Feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService cs;
	
	/**
	 *意见反馈添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="意见反馈添加 接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addFeedback(
    		@RequestBody FeedbackPO po) {
        return cs.add(po);
    }
	/**
	 *意见反馈删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="意见反馈删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteFeedBack(
    		@RequestBody List<FeedbackPO> pos) {
        return cs.deleteLists(pos);
    }
	/**
	 *意见反馈列表获取
	 * 
	 **/
	@ApiOperation(value="意见反馈列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getFeedbackList(@RequestBody FeedbackVo pos) {
        return cs.selectPageList(pos);
    }
}
