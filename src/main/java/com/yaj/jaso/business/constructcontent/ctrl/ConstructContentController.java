package com.yaj.jaso.business.constructcontent.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yaj.jaso.business.constructcontent.entity.po.ConstructContentPO;
import com.yaj.jaso.business.constructcontent.entity.vo.ConstructContentVo;
import com.yaj.jaso.business.constructcontent.service.ConstructContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="施工内容controller",tags="施工内容操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ConstructContent")
public class ConstructContentController {
	@Autowired
	private ConstructContentService cs;
	
	/**
	 *施工内容添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="施工内容添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addConstructContent(
    		@RequestBody ConstructContentPO ConstructContent) {		
        return cs.addOrUpdate(ConstructContent);
    }
	/**
	 *施工内容删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="施工内容删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteConstructContent(
    		@RequestBody List<ConstructContentPO> list) {
        return cs.deleteLists(list);
    }
	/**
	 *施工内容列表获取
	 * 
	 **/
	@ApiOperation(value="施工内容列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getConstructContentList(@RequestBody ConstructContentVo constructContent) {
        return cs.selectPageList(constructContent);
    }
	/**
	 *施工内容所有数据获取
	 * 
	 **/
	@ApiOperation(value="施工内容所有数据获取接口",notes="all获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getConstructContentLists(@RequestBody ConstructContentPO constructContent) {
        return cs.selectAll(constructContent);
    }
	/**
	 *施工内容tree获取
	 * 
	 **/
	@ApiOperation(value="施工内容所有数据获取接口",notes="all获取")
	@RequestMapping(value="/selectTree", method = RequestMethod.POST)
    public Object selectTree(@RequestBody ConstructContentPO constructContent) {
        return cs.selectTree(constructContent);
    }
	/*
	 *app根据当前用户权限获取施工内容
	 * 
	 */
	@ApiOperation(value="app根据当前用户权限获取施工内容",notes="all获取")
	@RequestMapping(value="/selectListByRole", method = RequestMethod.POST)
    public Object selectListByRole(
    		@RequestBody ConstructContentPO constructContent) {
        return cs.selectListByRole(constructContent);
    }
	/**
	 *工种-施工内容表单导入
	 * 
	 **/
	@ApiOperation(value="工种-施工内容表单导入接口",notes="工种-施工内容表单导入")
	@RequestMapping(value="/web/importConstructContent", method = RequestMethod.POST)
	public Object importConstructContent(
	    		 @RequestParam(value = "file", required = true) MultipartFile file,
	            HttpServletRequest request){
	    return cs.importConstructContent(file,request);
	}
}
