package com.yaj.common.generate;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.yaj.common.generate.base.GenerateFileUtil;
import com.yaj.core.properties.PropertiesListenerConfig;
import com.yaj.core.util.JSONUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/generate")
@ConditionalOnProperty(prefix = "base", name = "generate", havingValue = "true")
public class GenerateController {
	@Autowired
	private GenerateService generateService;
	
	@Value("${spring.datasource.url}")
	private String databaseUrl;

	@ApiOperation(value = "测试", notes = "测试")
	@RequestMapping(value = "/index")
	public ModelAndView index(String schema,String[] table,String moduleName,String target) throws IOException {

	    String moduleBasePath = GenerateFileUtil.mergePath(System.getProperty("user.dir"),"src","main","java","com","yaj");

		if(schema == null) {
			schema = databaseUrl.substring(databaseUrl.lastIndexOf("/")+1,databaseUrl.indexOf("?"));
		}
		if(table != null) {
			 generateService.processGeneretion(schema,moduleName,moduleBasePath,table);
		}
		
		Map map = new HashMap();
		String url = PropertiesListenerConfig.getProperty("spring.datasource.url");
		List list = generateService.getAllTableNames(schema);
		String[] tableNames = (String[]) list.toArray(new String[list.size()]);
		map.put("tableNames", tableNames);
		return new ModelAndView("/generate/index.html", map);
	}
}
