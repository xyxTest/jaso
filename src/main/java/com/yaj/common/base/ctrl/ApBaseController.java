package com.yaj.common.base.ctrl;
 
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.map.LinkedMap; 
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.BaseParameters;
import com.yaj.common.base.pojo.MulFactory;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.pojo.Operations;
import com.yaj.common.base.pojo.PageVo;
import com.yaj.common.base.pojo.WhereCustomSegment;
import com.yaj.common.base.service.ServiceMain;

import io.swagger.annotations.ApiOperation;

public abstract class ApBaseController<T extends ServiceImpl,V extends BaseParameters> {

	protected final static Integer AUTHORITY_INSERT = 0x0001;
	protected final static Integer AUTHORITY_DELETE = 0x0002;
	protected final static Integer AUTHORITY_UPDATE = 0x0003;
	protected final static Integer AUTHORITY_SEARCH = 0x0004;
	protected final static Integer AUTHORITY_IMPORT = 0x0005;
	protected final static Integer AUTHORITY_EXPORT = 0x0006;
	protected final static Integer AUTHORITY_UNIQUE = 0x0007;
	
	private final static PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
	
	public abstract Integer[] pageAuthority();
	@Autowired
	protected T service;
	@Autowired
	protected ServiceMain serviceMain;
	
	@ApiOperation(value = "模板添加", notes = "模板添加")
	@PostMapping(value = "/insert")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Object insert(@Valid @RequestBody V v, BindingResult result) throws Exception{
		if (!authority(AUTHORITY_INSERT)) { return -1; } 
		if (v.entities() == null || v.entities().length <= 0) {return -1;}
		Object entity = v.entities()[0];
		
		overBeforeInsert(entity);
		service.insertOrUpdate(entity); 
		overAfterInsert(entity);
		return 1;
	}
	
	@ApiOperation(value = "模板查询", notes = "模板查询")
	@PostMapping(value = "/search")
	public Object search(@Valid @RequestBody  V v, BindingResult result) throws Exception {
		
		if (!authority(AUTHORITY_SEARCH)) { return -1; }
		if (v.entities() == null || v.entities().length <= 0) {return -1;}
		
		if (v.entities().length == 1) {
			return simpleSearch(v);
		} else {
			return mulSearch(v);
		}
	}
	
	@ApiOperation(value = "模板查询", notes = "模板查询")
	@PostMapping(value = "/searchByIds")
	public Object searchIds(@RequestBody ArrayList<Integer> ids, BindingResult result) throws Exception {
		EntityWrapper ew = new EntityWrapper<>();
		ew.in("question_id", ids);
		 
		return service.selectList(ew);
	}
	
	private Object simpleSearch(V v) throws Exception {
		Object entity = v.entities()[0];
		EntityWrapper<?> ew = new EntityWrapper<>(); 
		
		if (v.getOrderBy() != null)
			ew.orderBy(v.getOrderBy());
		
		String nickName = MulFactory.getTableName(entity).replaceAll("_", "");
		PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(entity.getClass());
		
		for (PropertyDescriptor i : properties) {
			if (i.getName().equals("class")) continue;
			
			String opreation = v.operational("${" + nickName + "}." + i.getName());
			Object param = propertyUtilsBean.getProperty(entity, i.getName()); 
			
			if (opreation != null) {
				
				if (opreation.equals(Operations.LIKE)) { 
					
					ew.like(param != null,getTableColumn(i.getName()), param == null ? "" : param.toString());
					
				} else if (opreation.equals(Operations.NOT_LIKE)) {
					
					ew.notLike(param != null,getTableColumn(i.getName()), param == null ? "" : param.toString());
					
				} else if (opreation.equals(Operations.NOT_EQUALS)) {
					
					ew.ne(param != null,getTableColumn(i.getName()), param);
					
				} else if (opreation.equals(Operations.LEFT_THEN)) {
					
					ew.lt(param != null,getTableColumn(i.getName()), param);
					
				} else if (opreation.equals(Operations.LEFT_THEN_AND_EQUALS)) {
					
					ew.le(param != null,getTableColumn(i.getName()), param);
					
				} else if (opreation.equals(Operations.MORE_THEN)) {
					
					ew.gt(param != null,getTableColumn(i.getName()), param);
					
				} else if (opreation.equals(Operations.MORE_THEN_AND_EQUALS)) {
					
					ew.ge(param != null,getTableColumn(i.getName()), param);
					
				} 
			} else {
				
				ew.eq(param != null,getTableColumn(i.getName()), param);
				
			}
		}
		if (v.getPage() != null) {
			Page pages = service.selectPage(new Page(v.getPage().getPageNo(), v.getPage().getPageSize()), ew);
			Map<String,Object> backResult = new HashMap<>();
			PageVo page = v.getPage();
			page.setTotal(pages.getTotal());
				backResult.put("data", pages.getRecords());
				backResult.put("page", page);
			return backResult;
		} else {
			return service.selectList(ew);
		}
		
	}
	private Object mulSearch(V v) throws Exception{
		
		List<Object> tempEntities = new ArrayList<>(v.entities().length);
		for (Object entity : v.entities()) {
			tempEntities.add(entity.getClass().newInstance());
		}
		
		MulSelect mulSelect = MulSelect.newInstance(v.columns() == null ? allColumns(v.entities()) : v.columns(), tempEntities);
		if (v.getOrderBy() != null)
			mulSelect.setOrderBy(v.getOrderBy());
		for (int i = 0; i < v.entities().length; i ++) {
			Object entity = v.entities()[i];
			String nickName = MulFactory.getTableName(entity).replaceAll("_", "");
			String prefix = "${" + nickName + "}";
			WhereCustomSegment segment = mulSelect.where(prefix);
			PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(entity.getClass());
			
			for (PropertyDescriptor property : properties) {
				if (property.getName().equals("class")) continue;
				if (property.getName().equals("ifDelete")) segment.eq(property.getName(), 0);
				
				String opreation = v.operational(prefix +"." + property.getName());
				Object param = propertyUtilsBean.getProperty(entity, property.getName()); 
				if (opreation != null) { 
					if (opreation.equals(Operations.LIKE)) { 
						
						segment.like(param != null, property.getName(), param == null ? "" : param.toString());
						
					} else if (opreation.equals(Operations.NOT_LIKE)) {
						
						segment.notLike(param != null, property.getName(), param == null ? "" : param.toString());
						
					} else if (opreation.equals(Operations.NOT_EQUALS)) {
						
						segment.notEq(param != null, property.getName(), param);
						
					} 
//					else if (opreation.equals(Opreations.LEFT_THEN)) {
//						segment.(param != null,getTableColumn(property.getName()), param);
//					} if (opreation.equals(Opreations.LEFT_THEN_AND_EQUALS)) {
//						segment.le(param != null,getTableColumn(property.getName()), param);
//					} if (opreation.equals(Opreations.MORE_THEN)) {
//						segment.gt(param != null,getTableColumn(property.getName()), param);
//					} if (opreation.equals(Opreations.MORE_THEN_AND_EQUALS)) {
//						segment.ge(param != null,getTableColumn(property.getName()), param);
//					} 
				} else {
					segment.eq(param != null, property.getName() , param);
				}
			}
		}
		if (v.getPage() != null)
			mulSelect.setPage(v.getPage().getPageNo(), v.getPage().getPageSize());
		
		return serviceMain.mulSelect(mulSelect);
	}
	private String allColumns(Object[] entities) {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < entities.length;  i++) {
			sb.append("${").append(i).append("}");
			if (i + 1 < entities.length) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	@ApiOperation(value = "模板修改", notes = "模板修改")
	@PostMapping(value = "/update")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Object update(@Valid @RequestBody V v, BindingResult result) throws Exception{
		if (!authority(AUTHORITY_UPDATE)) { return -1; }
		if (v.entities() == null || v.entities().length <= 0) {return -1;}
		
		Object entity = v.entities()[0];
		overBeforeUpdate(entity);
		service.updateById(entity);
		overAfterUpdate(entity);
		return 1;
	}
	
	@ApiOperation(value = "模板删除", notes = "模板删除")
	@PostMapping(value = "/delete")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Object delete(@Valid @RequestBody V v, BindingResult result) throws Exception{
		if (!authority(AUTHORITY_DELETE)) { return -1; }
		if (v.entities() == null || v.entities().length <= 0) {return -1;}
		
		Object entity = v.entities()[0];
		
		Integer id = findId(entity);
		if (id == null) {
			return -1;
		}
		return service.deleteById(id);
	}
	//find entity id value by annotation @TableId
	private Integer findId(Object entity) throws Exception {
		String idName = MulFactory.getIdAnnotation(entity.getClass());
		if (idName != null) {
			Object id =  propertyUtilsBean.getProperty(entity, idName);
			if (id != null) {
				return (Integer) id;
			}
		}
		return null;
	}
	@ApiOperation(value = "导出数据", notes = "导出数据")
	@PostMapping(value = "/export")
	private Object export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!authority(AUTHORITY_EXPORT)) { return -1; }

		return 1;
	}
	@ApiOperation(value = "唯一验证", notes = "唯一验证")
	@PostMapping(value = "/unique")
	private Object unique(V v) throws Exception {
		if (!authority(AUTHORITY_UNIQUE)) { return -1; }
		if (v.entities() == null || v.entities().length <= 0) {return -1;}
		
		if (service.selectList(new EntityWrapper<>(v.entities()[0])).isEmpty()) {
			return 1;
		} else {
			return -1;
		}
	}
	protected String getTableColumn(String attribute) {  
		return attribute.replaceAll("[A-Z]", "_$0").toLowerCase();
			
	}
	private boolean authority(Integer method) {
		if (pageAuthority() == null) return true;
		
		return Arrays.asList(pageAuthority()).contains(method);
	}

	
	protected void overBeforeInsert(Object entity) {}
	protected void overAfterInsert(Object entity) {}
	
	protected void overBeforeUpdate(Object entity) {}
	protected void overAfterUpdate(Object entity) {}
}
	