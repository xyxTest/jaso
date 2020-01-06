package com.yaj.jaso.business.measureproblem.ctrl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemAdd;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemCheck;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemVo;
import com.yaj.jaso.business.measureproblem.service.MeasureProblemService;
import com.yaj.jaso.business.measureproblemuser.entity.po.MeasureProblemUserPO;
import com.yaj.jaso.business.measureproblemuser.entity.vo.MeasureProblemUserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="实测实量-爆点Controller",tags="实测实量-爆点")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MeasureProblem")
public class MeasureProblemController {
	@Autowired
	private MeasureProblemService ts;
	
	/**
	 *实测实量-爆点指派接口
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="实测实量-爆点指派接口",notes="指派接口")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMeasureProblem(
    		@RequestBody List<MeasureProblemAdd> po) {
        return ts.add(po);
    }
	/**
	 *实测实量-爆点删除
	 * 
	 **/
	@ApiOperation(value="实测实量-爆点删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteMeasureProblem(
    		@RequestBody List<MeasureProblemPO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *实测实量-爆点列表获取
	 * 
	 **/
	@ApiOperation(value="实测实量-爆点列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getMeasureProblemList(@RequestBody MeasureProblemVo po) {
        return ts.selectListByPage(po);
    }
	/**
	 *实测实量-爆点获取 
	 * 
	 */
	@ApiOperation(value="实测实量-爆点获取 接口",notes="爆点获取 ")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object selectList(@RequestBody MeasureProblemPO po) {
        return ts.selectList(po);
    }
	/**
	 *实测实量详情获取图纸和测点坐标 
	 **/
	@ApiOperation(value="实测实量-详情获取图纸和测点坐标 接口",notes="详情获取图纸和测点坐标获取 ")
	@RequestMapping(value="/selectById", method = RequestMethod.POST)
    public Object selectById(@RequestBody MeasureProblemPO po) {
        return ts.selectByIds(po);
    }
	/**
	 *实测实量-参与人列表获取接口 
	 */
	@ApiOperation(value="实测实量-爆点获取 接口",notes="爆点获取 ")
	@RequestMapping(value="/selectAboutUserList", method = RequestMethod.POST)
    public Object selectAboutUserList(@RequestBody MeasureProblemPO po) {
        return ts.selectAboutUserList(po);
    }
	/**
	 * 实测实量相关人员添加接口
	 */
	@ApiOperation(value="实测实量-相关人员添加接口",notes="爆点获取 ")
	@RequestMapping(value="/addAboutUser", method = RequestMethod.POST)
    public Object addAboutUser(@RequestBody MeasureProblemUserVo po) {
        return ts.addAboutUser(po);
    }
	/**
	 *爆点问题验收 
	 */
	@ApiOperation(value="实测实量-爆点清单-验收接口",notes="爆点清单-验收 ")
	@RequestMapping(value="/check", method = RequestMethod.POST)
    public Object checkMeasureProblem(@RequestBody MeasureProblemCheck po) {
        return ts.checkMeasureProblem(po);
    }
	/**
	 *爆点问题设置整改进度接口 
	 **/
	@ApiOperation(value="爆点问题设置整改进度接口 ",notes="爆点问题设置整改进度接口  ")
	@RequestMapping(value="/setProcess", method = RequestMethod.POST)
    public Object setMeasureProblemCheckProgress(@RequestBody MeasureProblemPO po) {
        return ts.setMeasureProblemCheckProgress(po);
    }
	/**
	 *新增参与人接口 
	 */
	@ApiOperation(value="新增参与人接口 ",notes="新增参与人接口")
	@RequestMapping(value="/addAboutUserList", method = RequestMethod.POST)
    public Object addAboutUserList(@RequestBody List<MeasureProblemUserPO> po) {
        return ts.addAboutUserList(po);
    }
}
