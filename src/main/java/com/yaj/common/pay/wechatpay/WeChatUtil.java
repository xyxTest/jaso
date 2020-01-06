package com.yaj.common.pay.wechatpay;
 
import java.io.UnsupportedEncodingException; 
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.yaj.common.pay.util.MD5Util; 
import cn.hutool.http.HttpUtil; 
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML; 
@Component("WeChatUtil")
public class WeChatUtil
{
    public final String TIME = "yyyyMMddHHmmss";
    
    @Value("${wechat.app_id}")
	private String appid;
    
    @Value("${wechat.mchid}")
    private String mchid;
    
    @Value("${wechat.notify_url}")
    private String notifyUrl;
    
    @Value("${wechat.server_url}")
    private String serverUrl;
    
    @Value("${wechat.key}")
    private String key;
 
    
    public String getRemoteHost(HttpServletRequest request) {
    	 String ip = request.getHeader("x-forwarded-for");
    	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
    	      ip = request.getHeader("Proxy-Client-IP");
    	    }
    	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
    	      ip = request.getHeader("WL-Proxy-Client-IP");
    	    }
    	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
    	      ip = request.getRemoteAddr();
    	    }
    	    return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
    
    public void pay(WeChatModel model) {   
    	
    	model.setAppid(appid);
    	model.setMch_id(mchid);
    	String key = model.toKeyString() + "&key=" + this.key;  
    	String sign = MD5Util.signature(key).toUpperCase();
		model.setSign(sign);
    	String json = JSON.toJSONString(model);
    	JSONObject jsonObj = new JSONObject(json); 
    	String wxXml =  "<xml>" + XML.toXml(jsonObj) + "</xml>"; 
		System.err.println(wxXml);

    	String result = HttpUtil.post(serverUrl, wxXml); 
    	cn.hutool.json.JSONObject wprp = XML.toJSONObject(result.replace("<xml>", "").replace("</xml>", ""));
    	
    	System.err.println(wprp.toString()); 
    }
   
    public String urlEncodeUTF8(String source)
    {
        String result = source;
        try
        {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    
    public  String getDateStr()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME);
        return sdf.format(new Date());
    }
 
} 