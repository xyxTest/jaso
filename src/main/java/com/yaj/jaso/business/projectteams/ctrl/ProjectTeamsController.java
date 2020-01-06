package com.yaj.jaso.business.projectteams.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.projectteams.entity.po.ProjectTeamsPO;
import com.yaj.jaso.business.projectteams.entity.vo.ProjectTeamsVo;
import com.yaj.jaso.business.projectteams.service.ProjectTeamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="班组controller",tags="班组操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ProjectTeams")
public class ProjectTeamsController {
	@Autowired
	private ProjectTeamsService cs;
	
	/**
	 *班组添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="班组添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addProjectTeams(
    		@RequestBody ProjectTeamsPO company) {
		
        return cs.insertOrUpdate(company);
    }
	/**
	 *班组删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="班组删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteProjectTeams(
    		@RequestBody List<ProjectTeamsPO> company) {
        return cs.deleteBatchByIds(company);
    }
	/**
	 *班组列表获取
	 * 
	 **/
	@ApiOperation(value="班组列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getProjectTeamsList(@RequestBody ProjectTeamsVo pt) {
        return cs.selectPageList(pt);
    }
	/**
	 *all班组获取
	 * 
	 **/
	@ApiOperation(value="all班组获取接口",notes="all获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getAllProjectTeamsList(@RequestBody ProjectTeamsPO pt) {
        return cs.selectAllList(pt);
    }
}
