package com.yaj.jaso.business.projecttenders.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.projecttenders.entity.po.ProjectTendersPO;
import com.yaj.jaso.business.projecttenders.entity.vo.ProjectTendersVo;
import com.yaj.jaso.business.projecttenders.service.ProjectTendersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="标段controller",tags="标段操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ProjectTenders")
public class ProjectTendersController {
	@Autowired
	private ProjectTendersService cs;
	
	/**
	 *标段添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="标段添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addProjectTenders(
    		@RequestBody ProjectTendersPO company) {
		
        return cs.insertOrUpdate(company);
    }
	/**
	 *标段删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="标段删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteProjectTenders(
    		@RequestBody List<ProjectTendersPO> po) {
        return cs.deleteBatchByIds(po);
    }
	/**
	 *标段列表获取
	 * 
	 **/
	@ApiOperation(value="标段列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getProjectTendersList(@RequestBody ProjectTendersVo po) {
        return cs.selectPageList(po);
    }
	/**
	 *标段列表获取
	 * 
	 **/
	@ApiOperation(value="所有标段获取接口",notes="所有标段")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getProjectTendersListByProjectId(@RequestBody ProjectTendersPO po) {
        return cs.selectListByProjectId(po);
    }
}
