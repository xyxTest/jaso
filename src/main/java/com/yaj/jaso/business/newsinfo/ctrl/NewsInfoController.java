package com.yaj.jaso.business.newsinfo.ctrl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.newsinfo.entity.po.NewsInfoPO;
import com.yaj.jaso.business.newsinfo.entity.vo.NewsInfoVo;
import com.yaj.jaso.business.newsinfo.service.NewsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="新闻资讯controller",tags="新闻资讯操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="NewsInfo")
public class NewsInfoController {
	@Autowired
	private NewsInfoService cs;
	
	/**
	 *新闻资讯添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="新闻资讯添加 接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addNewsInfo(
    		@RequestBody NewsInfoPO po) {
        return cs.add(po);
    }

	/**
	 *阅读量更新
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="阅读量更新接口",notes="阅读量更新")
	@RequestMapping(value="/update", method = RequestMethod.POST)
    public Object updateNewsInfo(
    		@RequestBody NewsInfoPO po) {
        return cs.update(po);
    }
	/**
	 *新闻资讯删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="新闻资讯删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteNewsInfo(
    		@RequestBody List<NewsInfoPO> pos) {
        return cs.deleteLists(pos);
    }
	/**
	 *新闻资讯列表获取
	 * 
	 **/
	@ApiOperation(value="新闻资讯列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getNewsInfoList(@RequestBody NewsInfoVo pos) {
        return cs.selectPageList(pos);
    }
}
