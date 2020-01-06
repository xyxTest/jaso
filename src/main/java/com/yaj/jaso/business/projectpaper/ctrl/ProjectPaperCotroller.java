package com.yaj.jaso.business.projectpaper.ctrl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yaj.jaso.business.projectpaper.entity.po.ProjectPaperPO;
import com.yaj.jaso.business.projectpaper.entity.vo.ProjectPaperVo;
import com.yaj.jaso.business.projectpaper.service.ProjectPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="项目图纸Controller",tags="项目图纸")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="ProjectPaper")
public class ProjectPaperCotroller {

	@Autowired
	private ProjectPaperService ts;
	
	/**
	 *项目图纸添加 
	 * 
	 **/
	@ApiOperation(value="项目图纸添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addProjectPaper(
    		@RequestBody ProjectPaperPO po) {
        return ts.add(po);
    }
	/**
	 *项目图纸删除
	 * 
	 **/
	@ApiOperation(value="项目图纸删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteProjectPaper(
    		@RequestBody List<ProjectPaperPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *项目图纸列表获取
	 * 
	 **/
	@ApiOperation(value="项目图纸列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getProjectPaperList(@RequestBody ProjectPaperVo po) {
        return ts.selectListByPage(po);
    }
	/**
	 *项目图纸列表获取
	 * 
	 **/
	@ApiOperation(value="项目图纸列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getProjectPaperLists(@RequestBody ProjectPaperPO po) {
        return ts.selectLists(po);
    }

}
