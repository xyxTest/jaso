package com.yaj.jaso.business.studyworkertype.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.studyworkertype.entity.po.StudyWorkerTypePO;
import com.yaj.jaso.business.studyworkertype.entity.vo.StudyWorkerTypeVo;
import com.yaj.jaso.business.studyworkertype.service.StudyWorkerTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="学习工种类型Controller",tags="学习工种类型")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyWorkerType")
public class StudyWorkerTypeController {
	@Autowired
	private StudyWorkerTypeService sts;
	
	/**
	 *工种类型添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="工种类型添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyWorkerType(
    		@RequestBody StudyWorkerTypePO po) {
		
        return sts.add(po);
    }
	/**
	 *工种类型删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="工种类型删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteStudyWorkerType(
    		@RequestBody List<StudyWorkerTypePO> po) {
        return sts.deleteBatchByIds(po);
    }
	/**
	 *工种类型列表获取
	 * 
	 **/
	@ApiOperation(value="工种类型列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getStudyWorkerTypeList(@RequestBody StudyWorkerTypeVo po) {
        return sts.selectListByPage(po);
    }
	/**
	 *工种类型列表获取
	 * 
	 **/
	@ApiOperation(value="工种类型列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getStudyWorkerTypeLists(@RequestBody StudyWorkerTypePO po) {
        return sts.selectLists(po);
    }
}
