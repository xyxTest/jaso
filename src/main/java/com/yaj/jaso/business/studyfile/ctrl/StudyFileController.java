package com.yaj.jaso.business.studyfile.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.studyfile.entity.po.StudyFilePO;
import com.yaj.jaso.business.studyfile.entity.vo.StudyFileAdd;
import com.yaj.jaso.business.studyfile.entity.vo.StudyFileVo;
import com.yaj.jaso.business.studyfile.service.StudyFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="学习资料Controller",tags="学习资料")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyFile")
public class StudyFileController {
	@Autowired
	private StudyFileService sf;
	
	/**
	 *学习资料添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习资料添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyFile(
    		@RequestBody StudyFileAdd po) {
		
        return sf.add(po);
    }
	/**
	 *学习资料删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习资料删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteStudyWorkerType(
    		@RequestBody List<StudyFilePO> po) {
        return sf.deleteBatchByIds(po);
    }
	/**
	 *学习资料列表获取
	 * 
	 **/
	@ApiOperation(value="学习资料列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getStudyWorkerTypeList(@RequestBody StudyFileVo po) {
        return sf.selectListByPage(po);
    }
	/**
	 *学习资料列表获取
	 * 
	 **/
	@ApiOperation(value="学习资料列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getStudyFileLists(@RequestBody StudyFilePO po) {
        return sf.selectLists(po);
    }
	/**
	 *学习资料详情获取
	 * 
	 **/
	@ApiOperation(value="学习资料详情获取接口",notes="详情获取")
	@RequestMapping(value="/getById", method = RequestMethod.POST)
    public Object getById(@RequestBody StudyFilePO po) {
        return sf.getById(po);
    }
}
