package com.yaj.jaso.business.studyanswerrecord.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.studyanswerrecord.entity.po.StudyAnswerRecordPO;
import com.yaj.jaso.business.studyanswerrecord.service.StudyAnswerRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="做题记录Controller",tags="做题记录")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyAnswerRecord")
public class StudyAnswerRecordController {
	@Autowired
	private StudyAnswerRecordService sf;
	
	/**
	 *做题记录添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="做题记录添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyAnswerRecord(
    		@RequestBody StudyAnswerRecordPO po) {
        return sf.add(po);
    }
	
	
	/**
	 *学习题目列表获取
	 * 
	 **/
	@ApiOperation(value="学习题目列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getStudyAnswerRecordLists(@RequestBody StudyAnswerRecordPO po) {
        return sf.selectLists(po);
    }
	
}
