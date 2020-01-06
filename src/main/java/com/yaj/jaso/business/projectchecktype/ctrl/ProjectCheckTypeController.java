package com.yaj.jaso.business.projectchecktype.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;
import com.yaj.jaso.business.projectchecktype.entity.vo.ProjectCheckTypeVo;
import com.yaj.jaso.business.projectchecktype.service.ProjectCheckTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="检查项Controller",tags="检查项")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ProjectCheckType")
public class ProjectCheckTypeController {
	@Autowired
	private ProjectCheckTypeService ts;
	
	/**
	 *检查项添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="检查项添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addProjectCheckType(
    		@RequestBody ProjectCheckTypePO po) {
		
        return ts.add(po);
    }
	/**
	 *检查项删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="检查项删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteProjectCheckType(
    		@RequestBody List<ProjectCheckTypePO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *轮播图片列表获取
	 * 
	 **/
	@ApiOperation(value="检查项列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getProjectCheckTypeList(@RequestBody ProjectCheckTypeVo po) {
        return ts.selectListByPage(po);
    }
	/**
	 *轮播图片列表获取
	 * 
	 **/
	@ApiOperation(value="检查项列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getProjectCheckTypeLists(@RequestBody ProjectCheckTypePO po) {
        return ts.selectLists(po);
    }
}
