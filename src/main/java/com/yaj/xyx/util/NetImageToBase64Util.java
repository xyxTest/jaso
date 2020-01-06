package com.yaj.xyx.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Encoder;

public class NetImageToBase64Util {
	public static void main(String[] args) {
		//第一个:把网络图片装换成Base64        
		String netImagePath = "http://jasobim.com/files/papers/257/05a870402427cc9552a143e1bffa11fc.png";
		//下面是网络图片转换Base64的方法        
		String strNetImageToBase64 = NetImageToBase64(netImagePath);
		System.out.println("网络图片转换Base64:"+strNetImageToBase64);
		/*//下面是本地图片转换Base64的方法        
		String imagePath = "本地图片路径";
		String strImageToBase64 =ImageToBase64(imagePath);
		System.out.println("本地图片转换Base64:"+strNetImageToBase64);*/ 
	}
	/**     * 网络图片转换Base64的方法     * @param netImagePath     */
	public static String NetImageToBase64(String netImagePath) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try { 
			// 创建URL            
			URL url = new URL(netImagePath);
			byte[] by = new byte[1024]; 
			// 创建链接            
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000000);
			InputStream is = conn.getInputStream();
			// 将内容读取内存中            
			int len = -1;
			while ((len = is.read(by)) != -1) {
				data.write(by, 0, len);
			}
			// 关闭流            
			is.close(); 
		} catch (IOException e) { 
			e.printStackTrace();
		}
		// 对字节数组Base64编码        
		BASE64Encoder encoder = new BASE64Encoder();
		String str = encoder.encode(data.toByteArray());
		return str;
	}
	/**     * 本地图片转换Base64的方法     * @param imgPath     */
	public static String ImageToBase64(String imgPath) { 
		InputStream in = null; 
		byte[] data = null;
		// 读取图片字节数组
		try { 
			in = new FileInputStream(imgPath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		// 对字节数组Base64编码        
		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串        
		String str=encoder.encode(data);
		return str;
	}
	

}
