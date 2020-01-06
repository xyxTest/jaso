package com.yaj.jaso.business.imagerotation.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.imagerotation.entity.po.ImageRotationPO;
import com.yaj.jaso.business.imagerotation.entity.vo.ImageRotationVo;
import com.yaj.jaso.business.imagerotation.service.ImageRotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="图片轮播Controller",tags="图片轮播")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ImageRotation")
public class ImageRotationController {
	@Autowired
	private ImageRotationService irs;
	
	/**
	 *轮播图片添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="轮播图片添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addImageRotation(
    		@RequestBody ImageRotationPO po) {
		
        return irs.add(po);
    }
	/**
	 *轮播图片删除
	 * 
	 **/
	@ApiOperation(value="轮播图片删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteImageRotation(
    		@RequestBody List<ImageRotationPO> po) {
        return irs.deleteBatchByIds(po);
    }
	/**
	 *轮播图片列表获取
	 * 
	 **/
	@ApiOperation(value="轮播图片列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getImageRotationList(@RequestBody ImageRotationVo po) {
        return irs.selectListByPage(po);
    }
}
