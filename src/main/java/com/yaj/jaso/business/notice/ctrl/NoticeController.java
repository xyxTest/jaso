package com.yaj.jaso.business.notice.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.notice.entity.po.NoticePO;
import com.yaj.jaso.business.notice.entity.vo.NoticePush;
import com.yaj.jaso.business.notice.entity.vo.NoticeVo;
import com.yaj.jaso.business.notice.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="公告Controller",tags="公告")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Notice")
public class NoticeController {

	@Autowired
	private NoticeService ts;
	
	/**
	 *公告添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="公告添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addNotice(
    		@RequestBody NoticePush po) {
        return ts.add(po);
    }
	/**
	 *公告删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="公告删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteNotice(
    		@RequestBody List<NoticePO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *公告列表获取
	 * 
	 **/
	@ApiOperation(value="检查性质列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getNoticeList(@RequestBody NoticeVo po) {
        return ts.selectListByPage(po);
    }
	
}
