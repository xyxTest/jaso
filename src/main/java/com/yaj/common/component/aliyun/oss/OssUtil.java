package com.yaj.common.component.aliyun.oss;
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream; 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.yaj.core.log.tools.LogHelper;

@Component("ossUtil")
public class OssUtil {
	
	@Value("${oss.endpoint}")
	private String endpoint;
	
	@Value("${oss.accessKeyId}")
	private String accessKeyId;
	
	@Value("${oss.accessKeySecret}")
	private String accessKeySecret;
	
	@Value("${oss.bucketName}")
	private String bucketName;
	
	@Value("${oss.folder}")
	private String folder;
	
	@Value("${oss.business}")
	private String business;
 
	public String upload(InputStream is,String fileName) {
		//创建client
		String key= folder + "/"+business + "/" + fileName;
		LogHelper.log("---------------------图片上传开始---------------------------------");
		OSSClient client=new OSSClient(endpoint, accessKeyId, accessKeySecret);
		client.putObject(bucketName, key, is);
		LogHelper.log("---------------------图片上传完毕---------------------------------");
		// 关闭client
		client.shutdown();
		return "/"+key;
	}
	 
	public void downLoad(String fileName,String downLoadPath) throws IOException {
		//创建client
		String key=folder+"/"+business+"/"+fileName;
		LogHelper.log("-------------------------------key:"+key+"---------------------------------------------");
		OSSClient client=new OSSClient(endpoint, accessKeyId, accessKeySecret);
		OSSObject ossObject=client.getObject(bucketName, key);
		client.getObject(new GetObjectRequest(bucketName, key), new File(downLoadPath));
		// 关闭client
		client.shutdown();
	}

}
