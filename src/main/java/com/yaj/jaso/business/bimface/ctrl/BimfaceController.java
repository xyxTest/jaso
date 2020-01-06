package com.yaj.jaso.business.bimface.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yaj.jaso.business.bimface.entity.ViewTokenAdd;
import com.yaj.jaso.business.bimface.service.BimfaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Bimface controller",tags="Bimface操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Bimface")
public class BimfaceController {
		@Autowired
		BimfaceService bimfaceService;
		/*
		 * 获取模型的展示码
		 * */
		@ApiOperation(value="获取模型的展示码接口",notes="获取模型的展示码")
	    @RequestMapping(value = "/getModelViewToken",method = RequestMethod.POST)
	    public Object getViewToken(
	    		@RequestBody ViewTokenAdd viewTokenAdd){
	        return bimfaceService.getViewTokenByFileId(viewTokenAdd);
	    }
	    
	    /*
	     * 上传模型并转换
	     * */
		@ApiOperation(value="上传模型并转换接口",notes="上传模型并转换")
	    @RequestMapping(value = "/uploadModelFile",method = RequestMethod.POST)
	    public Object uploadModelFile(
	    		@RequestParam(value="modelFile",required=true) MultipartFile[] modelFile,
	    		@RequestParam(value="projectId",required=true) Long projectId,
	    		HttpServletRequest request){
	        return bimfaceService.uploadModelFile(modelFile[0],request,projectId);
	    }
	    
	    /*
	     * 通过集成模型的id获取集成模型的展示码
	     * */
		@ApiOperation(value="通过集成模型的id获取集成模型的展示码接口",notes="通过集成模型的id获取集成模型的展示码")
	    @RequestMapping(value = "/getModeViewTokenByIntegrateId",method = RequestMethod.POST)
	    public Object getModeViewTokenByIntegrateId(
	    		@RequestParam(value="integrateId",required=true) Long integrateId,
	    		@RequestParam(value="projectId",required=true) Long projectId,
	    		HttpServletRequest request){
	        return bimfaceService.getModeViewTokenByIntegrateId(integrateId,request,projectId);
	    }
	    /*
	     * 根据fileId获取模型的专业分类构件信息
	     * */
		@ApiOperation(value="根据fileId获取模型的专业分类构件信息接口",notes="根据fileId获取模型的专业分类构件信息")
	    @RequestMapping(value="/getCategory",method= RequestMethod.GET)
	    public Object getCategory(
	    		@RequestParam(value="fileId",required=true) Long fileId,
	    		@RequestParam(value="projectId",required=false) Long projectId){
	        return bimfaceService.getCategory(fileId,projectId);
	    }
}
