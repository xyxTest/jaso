package com.yaj.jaso.business.subjectmaterial.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.subjectmaterial.entity.po.SubjectMaterialPO;
import com.yaj.jaso.business.subjectmaterial.entity.vo.SubjectMaterialVo;
import com.yaj.jaso.business.subjectmaterial.service.SubjectMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="主材料controller",tags="主材料操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="SubjectMaterial")
public class SubjectMaterialController {
	@Autowired
	private SubjectMaterialService cs;
	
	/**
	 *主材料添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="主材料添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addSubjectMaterial(
    		@RequestBody SubjectMaterialPO SubjectMaterial) {
		
        return cs.add(SubjectMaterial);
    }
	/**
	 *主材料删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="主材料删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteSubjectMaterial(
    		@RequestBody List<SubjectMaterialPO> SubjectMaterial) {
        return cs.deleteLists(SubjectMaterial);
    }
	/**
	 *主材料列表获取
	 * 
	 **/
	@ApiOperation(value="主材料列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getSubjectMaterialList(@RequestBody SubjectMaterialVo SubjectMaterial) {
        return cs.selectPageList(SubjectMaterial);
    }
	/**
	 *施工日志-all主材料获取
	 * 
	 **/
	@ApiOperation(value="施工日志-all主材料获取接口",notes="all获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getSubjectMaterialLists(@RequestBody SubjectMaterialPO subjectMaterial) {
        return cs.getSubjectMaterialList(subjectMaterial);
    }
}
