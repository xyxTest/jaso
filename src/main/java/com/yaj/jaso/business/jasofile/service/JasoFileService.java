package com.yaj.jaso.business.jasofile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasofile.entity.po.JasoFilePO;
import com.yaj.jaso.business.jasofile.mapper.JasoFileMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.xyx.util.MD5Util;

/*
 * @Description: 
 * @date: 2019-10-09
 */
@Service
public class JasoFileService extends ServiceImpl<JasoFileMapper, JasoFilePO> {

    @Resource
    JasoFileMapper jasoFileMapper;
    private final static String filePath = "jaso/file";
	public JasoFilePO uploadFile(String filePath, MultipartFile file, Integer fileType,HttpServletRequest request) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if (file == null || filePath == null || filePath.equals("")) {
			return null;
		}
		String rootPath = request.getSession().getServletContext().getRealPath("/");
        String newFileName = MD5Util.getMD5String(file.getOriginalFilename() + new Date() + UUID.randomUUID().toString()).replace(".","")
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //批量导入。参数：文件名，文件。
//        boolean b = itemService.batchImport(newFileName,file);
        File fileDir = new File(rootPath + "/" + filePath);
        JasoFilePO files=new JasoFilePO();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(rootPath + "/" + filePath + "/"
                    + newFileName);
                // 写入文件
            out.write(file.getBytes());
            out.flush();
            out.close();
            Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=format.format(date);
            files.setIntro(time);
            String realPath=filePath + "/"+ newFileName;
            files.setName(newFileName);//////构件的url
            files.setFileUrl(realPath);
            files.setFileType(fileType);
            files.setJasoUserId(cacheUser.getJasoUserId());
            files.setRealName(file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".")));
            insertOrUpdate(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
	}

	public Object getById(Long id) {
		// TODO Auto-generated method stub
		return getById(id);
	}
	public boolean deleteFileById(Long id) {
		return deleteById(id);
	}
	public boolean deleteFileByIdList(List<Long> idList) {
		// TODO Auto-generated method stub
		return deleteBatchIds(idList);
	}

	public Object uploadFile(MultipartFile file, HttpServletRequest request) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if (file == null || filePath == null || filePath.equals("")) {
			return null;
		}
		String rootPath = request.getSession().getServletContext().getRealPath("/");
        String newFileName = MD5Util.getMD5String(file.getOriginalFilename() + new Date() + UUID.randomUUID().toString()).replace(".","")
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //批量导入。参数：文件名，文件。
//        boolean b = itemService.batchImport(newFileName,file);
        File fileDir = new File(rootPath + "/" + filePath);
        JasoFilePO files=new JasoFilePO();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(rootPath + "/" + filePath + "/"
                    + newFileName);
                // 写入文件
            out.write(file.getBytes());
            out.flush();
            out.close();
            Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=format.format(date);
            files.setIntro(time);
            String realPath=filePath + "/"+ newFileName;
            files.setName(newFileName);//////构件的url
            files.setFileUrl(realPath);
            files.setFileType(1);
            files.setJasoUserId(cacheUser.getJasoUserId());
            files.setRealName(file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".")));
            insertOrUpdate(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> urlList = new ArrayList<String>();
        urlList.add(files.getFileUrl());
        return urlList;
	}
}
