package com.yaj.jaso.business.studydata.ctrl;

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

import com.yaj.jaso.business.studyanswerrecord.entity.po.StudyAnswerRecordPO;
import com.yaj.jaso.business.studydata.entity.po.StudyDataPO;
import com.yaj.jaso.business.studydata.entity.vo.PaperInport;
import com.yaj.jaso.business.studydata.entity.vo.SelectOption;
import com.yaj.jaso.business.studydata.entity.vo.StudyDataVo;
import com.yaj.jaso.business.studydata.service.StudyDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="学习题目Controller",tags="学习题目")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyData")
public class StudyDataController {
	@Autowired
	private StudyDataService sf;
	
	/**
	 *学习题目添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习题目添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyData(
    		@RequestBody StudyDataPO po) {
        return sf.add(po);
    }
	/**
	 *学习题目删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="学习题目删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteStudyData(
    		@RequestBody List<StudyDataPO> po) {
        return sf.deleteBatchByIds(po);
    }
	/**
	 *学习题目列表获取
	 * 
	 **/
	@ApiOperation(value="学习题目列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getStudyDataList(@RequestBody StudyDataVo po) {
        return sf.selectListByPage(po);
    }
	/**
	 *学习题目列表获取
	 * 
	 **/
	@ApiOperation(value="学习题目列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getStudyDataLists(@RequestBody PaperInport po) {
        return sf.selectLists(po);
    }
	/**
	 *根据试卷获取学习题目
	 * 
	 **/
	@ApiOperation(value="根据试卷获取学习题目接口",notes="试卷试题获取")
	@RequestMapping(value="/selectListByIds", method = RequestMethod.POST)
    public Object getStudyPaperList(@RequestBody SelectOption po) {
        return sf.getStudyPaperList(po);
    }
	/**
	 *试题导入 
	 */
	@ApiOperation(value="学习题目导入接口",notes="导入")
	@RequestMapping(value="/import", method = RequestMethod.POST)
    public Object importStudyData( 
    		@RequestParam(value = "file", required = true) MultipartFile file,
    		@RequestParam(value = "studyWorkerTypeId", required = true) Long studyWorkerTypeId,
            HttpServletRequest request) {
        return sf.importStudyData(file,request,studyWorkerTypeId);
    }
	/**
	 *答题精华 
	 */
	@ApiOperation(value="答题精华获取",notes="答题精华")
	@RequestMapping(value="/selectMainList",method=RequestMethod.POST)
	public Object selectMainList(@RequestBody StudyDataPO po){
		return sf.selectMainList(po);
	}
	/**
	 *试卷做题提交 
	 */
	@ApiOperation(value="试卷做题提交 接口",notes="试卷提交")
	@RequestMapping(value="/submitPaper",method=RequestMethod.POST)
	public Object submitPaper(@RequestBody List<StudyAnswerRecordPO> recordList){
		return sf.submitPaper(recordList);
	}
}
