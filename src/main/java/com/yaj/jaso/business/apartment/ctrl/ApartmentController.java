package com.yaj.jaso.business.apartment.ctrl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.apartment.service.ApartmentService;
import com.yaj.jaso.business.apartment.entity.po.ApartmentPO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="户型controller",tags="户型接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Apartment")
public class ApartmentController {
	@Autowired
	private ApartmentService aps;
	/**
	 *户型添加 
	 * 
	 **/
	@ApiOperation(value="户型添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addApartment(
    		@RequestBody ApartmentPO Apartment) {
        return aps.add(Apartment);
    }
	/**
	 *户型删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="户型删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteApartment(
    		@RequestBody List<ApartmentPO> list) {
        return aps.deleteList(list);
    }
	/**
	 *户型列表获取
	 * 
	 **/
	@ApiOperation(value="户型获取接口",notes="户型获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getApartmentList(@RequestBody ApartmentPO log) {
        return aps.selectPage(log);
    }
	/**
	 *所有户型获取
	 * 
	 **/
	@ApiOperation(value="所有户型获取接口",notes="所有户型获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getAllApartmentList(@RequestBody ApartmentPO log) {
        return aps.selectAll(log);
    }
}
