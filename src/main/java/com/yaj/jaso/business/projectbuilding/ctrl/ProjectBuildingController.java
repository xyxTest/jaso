package com.yaj.jaso.business.projectbuilding.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.projectbuilding.entity.po.ProjectBuildingPO;
import com.yaj.jaso.business.projectbuilding.entity.vo.ProjectBuildingAdd;
import com.yaj.jaso.business.projectbuilding.entity.vo.ProjectBuildingUpdate;
import com.yaj.jaso.business.projectbuilding.entity.vo.ProjectBuildingVo;
import com.yaj.jaso.business.projectbuilding.service.ProjectBuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="楼栋controller",tags="楼栋操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ProjectBuilding")
public class ProjectBuildingController {
	@Autowired
	private ProjectBuildingService cs;
	
	/**
	 *楼栋添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="楼栋添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addProjectBuilding(
    		@RequestBody ProjectBuildingPO company) {
       
		return cs.add(company);
    }
	/**
	 * 楼栋编辑
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="楼栋编辑接口",notes="编辑")
	@RequestMapping(value="/edit", method = RequestMethod.POST)
    public Object editProjectBuilding(
    		@RequestBody ProjectBuildingAdd po) {
       
		return cs.editProjectBuilding(po);
    }
	/**
	 *楼栋删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="楼栋删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteProjectBuilding(
    		@RequestBody List<ProjectBuildingPO> company) {
        return cs.deleteBatchByIds(company);
    }
	/**
	 *查询接口 
	 */
	@ApiOperation(value="组织架构查询接口 ",notes="查询")
	@RequestMapping(value="/selectByPid",method=RequestMethod.POST)
	public Object selectProjectBuildingByPid(
			@RequestBody ProjectBuildingPO dpo){
		return cs.selectLists(dpo);
	}
	
	/**
	 *楼栋列表获取
	 * 
	 **/
	@ApiOperation(value="楼栋列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getProjectBuildingList(@RequestBody ProjectBuildingVo company) {
        return cs.selectPageList(company);
    }
	/**
	 *楼栋list
	 *获取
	 * 
	 **/
	@ApiOperation(value="楼栋list获取接口",notes="list获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getProjectBuildingInfoList(@RequestBody ProjectBuildingPO company) {
        return cs.selectInfoList(company);
    }
	/**
	 *楼栋列表结构树获取
	 * 
	 **/
	@ApiOperation(value="楼栋列表结构树获取接口",notes="楼栋列表结构树获取")
	@RequestMapping(value="/selectTree", method = RequestMethod.POST)
    public Object getProjectBuildingListTree(@RequestBody ProjectBuildingPO company) {
        return cs.selectListTree(company);
    }
	/*分页获取楼栋下的所有房间*/
	@ApiOperation(value="获取当前节点的所有子节点",notes="获取当前节点的所有子节点")
	@RequestMapping(value="/selectListByPid", method = RequestMethod.POST)
    public Object selectListByPid(@RequestBody ProjectBuildingVo company) {
        return cs.selectListByPid(company);
    }
	/**
	 *删除当前节点及其所有子节点 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="删除当前节点及其所有子节点 接口",notes="删除")
	@RequestMapping(value="/deleteByPid", method = RequestMethod.POST)
    public Object deleteByPid(
    		@RequestBody ProjectBuildingPO po) {
		return cs.deleteChildByPid(po);
    }
	/**
	 *楼栋信息修改 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="楼栋信息修改 接口",notes="修改")
	@RequestMapping(value="/update", method = RequestMethod.POST)
    public Object updatePaper(
    		@RequestBody ProjectBuildingUpdate po) {
		return cs.updatePaper(po);
    }
	
}
