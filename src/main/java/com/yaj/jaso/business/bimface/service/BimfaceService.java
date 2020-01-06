package com.yaj.jaso.business.bimface.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.yaj.jaso.business.bimface.entity.ViewTokenAdd;
import com.yaj.jaso.business.jasofile.entity.po.JasoFilePO;
import com.yaj.jaso.business.jasofile.service.JasoFileService;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.project.service.ProjectService;
import com.bimface.sdk.BimfaceClient;
import com.bimface.sdk.bean.request.FileUploadRequest;
import com.bimface.sdk.bean.response.CategoryBean;
import com.bimface.sdk.bean.response.FileBean;
import com.bimface.sdk.bean.response.TranslateBean;
import com.bimface.sdk.exception.BimfaceException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BimfaceService {
	
	@Autowired
	JasoFileService filesService;
	@Autowired
	ProjectService projectService;
	private static final String appKey="OWmpvu5oCSHliFJuOJGFBscAtPPZcsBW";
	private static final String appSecret="SAiqdPlw5AI72SvARjkG7TJHWzAUwkeB";
	public Object getViewTokenByFileId(ViewTokenAdd viewToken) {
		BimfaceClient bimfaceClient =new BimfaceClient(appKey,appSecret);
		String str="";
		try {
			str = bimfaceClient.getViewTokenByFileId(viewToken.getFileId());
		} catch (BimfaceException e) {
			e.printStackTrace();
		}
		return str;
	}
	public Object uploadModelFile(MultipartFile file,HttpServletRequest requestion,Long projectId) {
		
		String filepath="bimface/file";
		JasoFilePO files=filesService.uploadFile(filepath, file, 0, requestion);
		FileUploadRequest fileUploadRequest = new FileUploadRequest();
		////
		fileUploadRequest.setUrl("http://jasobim.com:8080/"+files.getFileUrl());
		////////////
		fileUploadRequest.setName(files.getName());
		FileBean fileBean=new FileBean();
		BimfaceClient bimfaceClient =new BimfaceClient(appKey,appSecret);
		try {
			fileBean=bimfaceClient.upload(fileUploadRequest);
		} catch (BimfaceException e1) {
			e1.printStackTrace();
		}
		  // 获取fileId
        Long fileId = fileBean.getFileId();
        if(fileId!=null){
        	ProjectPO project = projectService.selectById(projectId);
        	if(project!=null){
        		project.setFileId(fileId.toString());
        		projectService.insertOrUpdate(project);
        	}
        }
        // 发起文件转换
        TranslateBean translateBean = null;
        try {
            translateBean = bimfaceClient.translate(fileId);
        } catch (BimfaceException e) {
        }
		return translateBean;
	}

	public Object getModeViewTokenByIntegrateId(Long integrateId, HttpServletRequest request,Long projectId) {
		String str="";
		try {
			BimfaceClient bimfaceClient =new BimfaceClient(appKey,appSecret);
			str = bimfaceClient.getViewTokenByIntegrateId(integrateId);
		} catch (BimfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	public Object getCategory(Long fileId, Long projectId) {
		List<CategoryBean> getResult = new ArrayList<CategoryBean>();
		try {
			BimfaceClient bimfaceClient =new BimfaceClient(appKey,appSecret);
			getResult = bimfaceClient.getCategory(fileId);
		} catch (BimfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getResult;
	}

}

