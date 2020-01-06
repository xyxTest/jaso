package com.yaj.jaso.business.studypersonalpreference.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.studypersonalpreference.entity.po.StudyPersonalPreferencePO;
import com.yaj.jaso.business.studypersonalpreference.service.StudyPersonalPreferenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="个人喜好Controller",tags="个人喜好")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="StudyPersonalPreference")
public class StudyPersonalPreferenceController {
	@Autowired
	private StudyPersonalPreferenceService sf;

	/**
	 *个人喜好添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="个人喜好添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addStudyPersonalPreference(
    		@RequestBody List<StudyPersonalPreferencePO> list) {
		
        return sf.add(list);
    }
	/**
	 *个人喜好删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="个人喜好删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteStudyPersonalPreference(
    		@RequestBody List<StudyPersonalPreferencePO> po) {
        return sf.deleteBatchByIds(po);
    }
	/**
	 *个人喜好列表获取
	 * 
	 **/
	@ApiOperation(value="个人喜好列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getStudyPersonalPreferenceList(@RequestBody StudyPersonalPreferencePO po) {
        return sf.selectLists(po);
    }
}
