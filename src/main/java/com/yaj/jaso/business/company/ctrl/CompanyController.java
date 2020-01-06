package com.yaj.jaso.business.company.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.plugins.Page;
import com.yaj.jaso.business.company.entity.po.CompanyPO;
import com.yaj.jaso.business.company.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="公司controller",tags="公司操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Company")
public class CompanyController {
	@Autowired
	private CompanyService cs;
	
	/**
	 *公司添加 
	 * 
	 **/
	@ApiOperation(value="公司添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addCompany(
    		@RequestBody CompanyPO company) {
        return cs.insert(company);
    }
	/**
	 *公司删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="公司删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteCompany(
    		@RequestBody CompanyPO company) {
		System.out.println("test!!");
        return cs.deleteById(company.getCompanyId());
    }
	/**
	 *公司列表获取
	 * 
	 **/
	@ApiOperation(value="公司列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getCompanyList(@RequestBody CompanyPO company) {
		Page<CompanyPO> page = new Page<CompanyPO>();
        return cs.selectPage(page);
    }
}
