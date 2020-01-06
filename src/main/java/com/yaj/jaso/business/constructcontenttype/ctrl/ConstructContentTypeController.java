package com.yaj.jaso.business.constructcontenttype.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.constructcontenttype.entity.po.ConstructContentTypePO;
import com.yaj.jaso.business.constructcontenttype.entity.vo.ConstructContentTypeVo;
import com.yaj.jaso.business.constructcontenttype.service.ConstructContentTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="施工内容类型controller",tags="施工内容类型操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ConstructContentType")
public class ConstructContentTypeController {
	@Autowired
	private ConstructContentTypeService cs;
	
	/**
	 *施工内容类型添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="施工内容类型添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addConstructContentType(
    		@RequestBody ConstructContentTypePO company) {
		
        return cs.add(company);
    }
	/**
	 *施工内容类型删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="施工内容类型删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteConstructContentType(
    		@RequestBody List<ConstructContentTypePO> company) {
        return cs.deleteLists(company);
    }
	/**
	 *施工内容类型列表获取
	 * 
	 **/
	@ApiOperation(value="施工内容类型列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getConstructContentTypeList(@RequestBody ConstructContentTypeVo company) {
        return cs.selectPageList(company);
    }
	/**
	 *all施工内容类型获取
	 * 
	 **/
	@ApiOperation(value="all施工内容类型获取接口",notes="all获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getAllConstructContentTypeList(@RequestBody ConstructContentTypePO contentType) {
        return cs.selectAllList(contentType);
    }
}
