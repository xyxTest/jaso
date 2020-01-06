package com.yaj.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yaj.common.component.aliyun.oss.OssUtil;

@Component("imageUtil")
public class ImageUtil {
	@Autowired
	private OssUtil ossUtil;
	
	/**
	 * @Title: upLoadImageToOss
	 * @Description: 上传图片
	 * @author 沈何均
	 * @date 2018年1月19日 下午1:42:00
	 * @company 北京帮众信息技术有限公司
	 * @param files
	 * @return
	 * @throws IOException 
	 */
	public List<String> upLoadImageToOss(MultipartFile[] files) throws IOException {
		List<String> paths=new ArrayList<String>();
		for(int i=0;i<files.length;i++) {
			MultipartFile file=files[i];
			String fileName=getFileName(file);
			InputStream is=file.getInputStream();
			String path=ossUtil.upload(is, fileName);
			paths.add(path);
		}
		return paths;
	}
	/**
	 * @Title: downLoad
	 * @Description: 下载文件到指定目录
	 * @author 沈何均
	 * @date 2018年1月19日 下午2:55:11
	 * @company 北京帮众信息技术有限公司
	 * @param fileName
	 * @param downLoadPath
	 * @throws IOException
	 */
	public void downLoad(String fileName, String downLoadPath) throws IOException {
		ossUtil.downLoad(fileName, downLoadPath);
	}
	
	/**
	 * @Title: getFileName
	 * @Description: 重新生成文件名
	 * @author 沈何均
	 * @date 2018年1月19日 下午1:40:33
	 * @company 北京帮众信息技术有限公司
	 * @param file
	 * @return
	 */
	private String getFileName(MultipartFile file) {
		String s2 =UUID.randomUUID().toString();
	    String UUName=s2.substring(0,8)+s2.substring(9,13)+s2.substring(14,18);
		String fileName="";
		String originalName=file.getOriginalFilename();
		int index=originalName.lastIndexOf(".");
		if(index<0) {
			//无后缀
			fileName=UUName;
		}else {
			fileName=UUName+originalName.substring(index);
		}
		return fileName;
	}

}
