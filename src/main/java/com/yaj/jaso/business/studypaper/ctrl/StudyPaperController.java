package com.yaj.jaso.business.studypaper.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.studypaper.entity.po.StudyPaperPO;
import com.yaj.jaso.business.studypaper.entity.vo.StudyPaperVo;
import com.yaj.jaso.business.studypaper.service.StudyPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="学习试卷Controller",tags="学习试卷")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyPaper")
public class StudyPaperController {
	@Autowired
	private StudyPaperService sf;

	/**
	 *学习试卷添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习试卷添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyPaper(
    		@RequestBody StudyPaperPO po) {
		
        return sf.add(po);
    }
	/**
	 *学习试卷删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习试卷删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteStudyPaper(
    		@RequestBody List<StudyPaperPO> po) {
        return sf.deleteBatchByIds(po);
    }
	/**
	 *学习试卷列表获取
	 * 
	 **/
	@ApiOperation(value="学习试卷列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getStudyPaperList(@RequestBody StudyPaperVo po) {
        return sf.selectListByPage(po);
    }
	/**
	 *学习试卷列表所有获取
	 * 
	 **/
	@ApiOperation(value="学习试卷所有获取接口",notes="所有获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getStudyPaperLists(@RequestBody StudyPaperPO po) {
        return sf.selectLists(po);
    }
}
