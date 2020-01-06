package com.yaj.jaso.business.workdepartmentrule.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.pojo.PageVo;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.department.entity.po.DepartmentPO;
import com.yaj.jaso.business.userdepartment.entity.po.UserDepartmentPO;
import com.yaj.jaso.business.workdepartmentrule.entity.po.WorkDepartmentRulePO;
import com.yaj.jaso.business.workdepartmentrule.service.WorkDepartmentRuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="",tags="")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="WorkDepartmentRule")
public class WorkDepartmentRuleController {
	@Resource
	private WorkDepartmentRuleService workDepartmentRuleService;
	@Resource
	private ServiceMain service;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="添加")
	@RequestMapping(value="/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody WorkDepartmentRulePO po) {
        return workDepartmentRuleService.insertOrUpdate(po);
    }
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="options")
	@RequestMapping(value="/options", method = RequestMethod.POST)
    public Object options() {
		Map<String, String> options = new HashMap<>();
		workDepartmentRuleService.selectList(new EntityWrapper<>()).forEach(el -> {
			options.put(el.getWorkDepartmentRuleId() + "", el.getRuleName());
		});
        return options;
    }
	
	private static class Vo extends WorkDepartmentRulePO {
		private PageVo page;

		public PageVo getPage() {
			return page;
		}
		public void setPage(PageVo page) {
			this.page = page;
		}
		
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="添加")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object select(@RequestBody Vo vo) {
		WorkDepartmentRulePO po = new WorkDepartmentRulePO();
		BeanUtil.copy(vo, po);
		MulSelect mulSelect = MulSelect.newInstance("${2}", po, new UserDepartmentPO(), new DepartmentPO());
		
		if (vo.getPage() != null)
			mulSelect.setPage(vo.getPage().getPageNo(), vo.getPage().getPageSize());
		
        return service.mulSelect(mulSelect);
        
    }
}
