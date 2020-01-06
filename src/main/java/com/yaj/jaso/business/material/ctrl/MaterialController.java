package com.yaj.jaso.business.material.ctrl;
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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yaj.jaso.business.material.entity.po.MaterialPO;
import com.yaj.jaso.business.material.entity.vo.MaterialVo;
import com.yaj.jaso.business.material.service.MaterialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物资controller",tags="物资操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Material")
public class MaterialController {
	@Autowired
	private MaterialService ms;
	
	/**
	 *物资添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMaterial(
    		@RequestBody MaterialPO company) {
		company.setLeaveNum(0);
        return ms.insertOrUpdate(company);
    }
	/**
	 *物资批量移动
	 * 
	 **/
	@ApiOperation(value="物资批量移动接口",notes="添加")
	@RequestMapping(value="/updateList", method = RequestMethod.POST)
    public Object updateListMaterial(
    		@RequestBody List<MaterialPO> poList) {
        return ms.updateAllColumnBatchById(poList);
    }
	/**
	 *物资删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMaterial(
    		@RequestBody List<MaterialPO> company) {
        return ms.deleteListById(company);
    }
	/**
	 *物资列表获取
	 * 
	 **/
	@ApiOperation(value="物资列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMaterialList(@RequestBody MaterialVo company) {
        return ms.selectLists(company);
    }
	/**
	 *物资列表获取
	 * 
	 **/
	@ApiOperation(value="所有物资物资获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getMaterialLists(@RequestBody MaterialPO company) {
		Wrapper<MaterialPO> wrapper = new EntityWrapper<MaterialPO>();
        return ms.selectList(wrapper);
    }
	/**
	 *物资表单导入
	 * 
	 **/
	@ApiOperation(value="物资表单导入接口",notes="物资表单导入")
	@RequestMapping(value="/web/importMaterial", method = RequestMethod.POST)
	public Object importMaterialList(
	    		 @RequestParam(value = "file", required = true) MultipartFile file,
	    		 @RequestParam(value = "projectId", required = true) Long projectId,
	            HttpServletRequest request){
	    return ms.importMaterial(file,request,projectId);
	}
	/**
	 *手机端扫描录入
	 * 
	 **/
	@ApiOperation(value="手机端扫描录入",notes="物资表单导入")
	@RequestMapping(value="/app/importMaterial", method = RequestMethod.POST)
    public Object importMaterialFile(
	    		 @RequestParam(value = "fileUrl", required = false) String fileUrl,
	    		 @RequestParam(value = "htmlUrl", required = false) String htmlUrl,
	             @RequestBody MaterialVo company){
	    return ms.importAppMaterial(htmlUrl,fileUrl,company);
	}
}
