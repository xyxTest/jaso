package com.yaj.jaso.business.materialtype.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.materialtype.entity.MaterialTypePO;
import com.yaj.jaso.business.materialtype.entity.vo.MaterialTypeVo;
import com.yaj.jaso.business.materialtype.service.MaterialTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物资分类controller",tags="物资分类操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MaterialType")
public class MaterialTypeController {
	@Autowired
	private MaterialTypeService ms;
	
	/**
	 *物资分类添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资分类添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMaterialType(
    		@RequestBody MaterialTypePO company) {
		
        return ms.insertOrUpdate(company);
    }
	/**
	 *物资分类删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资分类删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMaterialType(
    		@RequestBody List<MaterialTypePO> company) {
        return ms.deleteTypeList(company);
    }
	/**
	 *物资分类列表获取
	 * 
	 **/
	@ApiOperation(value="物资分类列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMaterialTypeList(@RequestBody MaterialTypePO company) {
   
		return ms.selectPageList(company);
    }
	/**
	 *物资分类列表获取
	 * 
	 **/
	@ApiOperation(value="物资分类列表分页获取接口",notes="列表获取")
	@RequestMapping(value="/selectPage", method = RequestMethod.POST)
    public Object selectPage(@RequestBody MaterialTypeVo company) {
   
		return ms.selectPageLists(company);
    }
}
