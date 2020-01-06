package com.yaj.jaso.business.studyimage.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.studyimage.entity.po.StudyImagePO;
import com.yaj.jaso.business.studyimage.entity.vo.StudyImageVo;
import com.yaj.jaso.business.studyimage.service.StudyImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="学习图片轮播Controller",tags="学习图片轮播")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyImage")
public class StudyImageController {
	
	@Autowired
	private StudyImageService sf;

	/**
	 *学习图片轮播添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习图片轮播添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyImage(
    		@RequestBody StudyImagePO po) {
		
        return sf.add(po);
    }
	/**
	 *学习图片轮播删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习图片轮播删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteStudyImage(
    		@RequestBody List<StudyImagePO> po) {
        return sf.deleteBatchByIds(po);
    }
	/**
	 *学习图片轮播列表获取
	 * 
	 **/
	@ApiOperation(value="学习图片轮播列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getStudyImageList(@RequestBody StudyImageVo po) {
        return sf.selectListByPage(po);
    }
	
}
