package com.yaj.jaso.business.nature.ctrl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.nature.entity.po.NaturePO;
import com.yaj.jaso.business.nature.entity.vo.NatureVo;
import com.yaj.jaso.business.nature.service.NatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="检查性质Controller",tags="检查性质")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Nature")
/*质量、安全管理的性质表*/
public class NatureController {
	@Autowired
	private NatureService ts;
	
	/**
	 *检查性质添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="检查性质添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addNature(
    		@RequestBody NaturePO po) {
		
        return ts.add(po);
    }
	/**
	 *检查性质删除
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="检查性质删除接口",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object deleteNature(
    		@RequestBody List<NaturePO> po) {
        return ts.deleteBatchByIds(po);
    }
	/**
	 *检查性质列表获取
	 * 
	 **/
	@ApiOperation(value="检查性质列表获取接口",notes="列表获取")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object getNatureList(@RequestBody NatureVo po) {
        return ts.selectListByPage(po);
    }
	/**
	 *检查性质列表获取
	 * 
	 **/
	@ApiOperation(value="检查性质列表获取接口",notes="列表获取")
	@RequestMapping(value="/selectList", method = RequestMethod.POST)
    public Object getNatureLists(@RequestBody NaturePO po) {
        return ts.selectLists(po);
    }
}
