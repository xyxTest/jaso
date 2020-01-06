package com.yaj.jaso.business.userproject.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.userproject.service.UserProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="用户项目关系表接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="UserProject")
public class UserProjectController {
	@Autowired
	UserProjectService ups;
	/***/
	@ApiOperation(value="获取用户所属项目列表接口",notes="获取项目列表")
	@RequestMapping(value="/selectProjectList",method=RequestMethod.POST)
    public Object selectProjectList(
    		@RequestBody JasoUserPO po) {
		
        return ups.selectProjectList(po);
    }
}
