package com.yaj.jaso.business.existingproblem.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.existingproblem.entity.po.ExistingProblemPO;
import com.yaj.jaso.business.existingproblem.entity.vo.ExistingProblemVo;
import com.yaj.jaso.business.existingproblem.service.ExistingProblemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="施工日志-存在问题controller",tags="施工日志-存在问题操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ExistingProblem")
public class ExistingProblemController {
	@Autowired
	private ExistingProblemService cs;
	
	/**
	 *施工日志-存在问题添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="施工日志-存在问题添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addExistingProblem(
    		@RequestBody ExistingProblemPO ExistingProblem) {
        return cs.add(ExistingProblem);
    }
	/**
	 *施工日志-存在问题删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="施工日志-存在问题删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteExistingProblem(
    		@RequestBody List<ExistingProblemPO> existingProblem) {
        return cs.deleteLists(existingProblem);
    }
	/**
	 *施工日志-存在问题列表获取
	 * 
	 **/
	@ApiOperation(value="施工日志-存在问题列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getExistingProblemList(@RequestBody ExistingProblemVo existingProblem) {
        return cs.selectPageList(existingProblem);
    }
	/**
	 *施工日志-存在问题列表获取
	 * 
	 **/
	@ApiOperation(value="施工日志-存在问题all获取接口",notes="all获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getExistingProblemLists(@RequestBody ExistingProblemPO existingProblem) {
        return cs.getExistingProblemList(existingProblem);
    }
}
