package com.yaj.jaso.business.materialimportlog.ctrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.materialimportlog.entity.po.MaterialImportLogPO;
import com.yaj.jaso.business.materialimportlog.service.MaterialImportLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="物资表单controller",tags="吴子表单操作接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="MaterialImportLog")
public class MaterialImportLogController {
	@Autowired
	private MaterialImportLogService cs;
	
	/**
	 *物资表单添加 
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="物资表单添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addMaterialImportLog(
    		@RequestBody MaterialImportLogPO company) {		
        return cs.insert(company);
    }
}
