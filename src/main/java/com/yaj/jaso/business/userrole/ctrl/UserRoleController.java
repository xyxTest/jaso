package com.yaj.jaso.business.userrole.ctrl;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value="用户权限关系表接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="UserRole")
public class UserRoleController {
	
}
