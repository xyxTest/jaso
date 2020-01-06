package com.yaj.jaso.business.jasofile.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.yaj.jaso.business.jasofile.service.JasoFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = {"http://localhost:8089/jaso", "null"})
@Api(value="文件上传Controller",tags="文件上传")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="JasoFile")
public class JasoFileController {
	@Autowired
	private JasoFileService fileService;
	/**
	 *文件上传
	 * 
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="文件上传接口",notes="上传")
	@RequestMapping(value="/upload", method = RequestMethod.POST)
    public Object uploadFile(
    		@RequestParam(value = "file", required = true) MultipartFile file,
            HttpServletRequest request) {
		
        return fileService.uploadFile(file,request);
    }
	
}
