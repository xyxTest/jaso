package com.yaj.jaso.business.studypractice.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.studypractice.entity.po.StudyPracticePO;
import com.yaj.jaso.business.studypractice.entity.vo.RecordModo;
import com.yaj.jaso.business.studypractice.service.StudyPracticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="每日一练Controller",tags="每日一练")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyPractice")
public class StudyPracticeController {
	@Autowired
	private StudyPracticeService sf;

	/**
	 *每日一练添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="每日一练添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyPractice(
    		@RequestBody StudyPracticePO po) {
        return sf.add(po);
    }
	
	/**
	 *每日一练列表获取
	 * 
	 **/
	@ApiOperation(value="每日一练列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getStudyPracticeList(@RequestBody StudyPracticePO po) {
        return sf.selectLists(po);
    }
	/**
	 *每日一练做题记录提交
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="每日一练做题记录提交接口",notes="提交")
	@RequestMapping(value="/submit", method = RequestMethod.POST)
    public Object submit(
    		@RequestBody RecordModo po) {
        return sf.submit(po);
    }
}
