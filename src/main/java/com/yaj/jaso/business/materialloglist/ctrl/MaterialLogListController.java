package com.yaj.jaso.business.materialloglist.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.materialloglist.entity.vo.MaterialLogListVo;
import com.yaj.jaso.business.materialloglist.service.MaterialLogListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物资大概记录controller",tags="物资大概记录操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MaterialLogList")
public class MaterialLogListController {
	@Autowired
	private MaterialLogListService mls;
	
	
	/**
	 *物资记录列表获取
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资记录列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMaterialLogLists(@RequestBody MaterialLogListVo mll) {
		return mls.selectLists(mll);
    }
}
