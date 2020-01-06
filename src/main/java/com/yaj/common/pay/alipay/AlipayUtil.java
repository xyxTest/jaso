package com.yaj.common.pay.alipay;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.yaj.core.log.tools.LogHelper;

@Component("AlipayUtil")
public class AlipayUtil {
	//商户appid
	@Value("${ali.app_id}")
	private String appId;
	
	@Value("${ali.server_url}")
	private String serverUrl;
	//私钥
	@Value("${ali.private_key}")
	private String privateKey;
	@Value("${ali.public_key}")
	private String publicKey;
//	//商家id
//	@Value("${ali.seller_id}")
//	private String sellerId; 
//	
	@Value("${ali.notify_url}")
	private String notifyUrl;
	
	private String character = "utf-8";
	
	public String pay(AlipayModel model) {
		try {
			AlipayClient client = new DefaultAlipayClient(serverUrl, appId, privateKey, "json", character, publicKey,"RSA2");
			AlipayTradeAppPayRequest aliRequest = new AlipayTradeAppPayRequest();
			
			AlipayTradeAppPayModel aliMode = new AlipayTradeAppPayModel();
			
			aliMode.setPassbackParams(URLEncoder.encode(model.getOrderBody()));
			aliMode.setOutTradeNo(model.getOrderNo() == null ? UUID.randomUUID().toString().replaceAll("-", "").toLowerCase() : model.getOrderNo());
			aliMode.setBody(model.getOrderBody());
			aliMode.setSubject(model.getOrderTitle());
			aliMode.setTotalAmount(model.getOrderPrice().toString());
			aliMode.setProductCode(model.getProductCode());
			
			aliRequest.setBizModel(aliMode);
			aliRequest.setNotifyUrl(notifyUrl); 
			
			AlipayTradeAppPayResponse response = client.sdkExecute(aliRequest);
			return response.getBody();
		} catch (Exception e) { 
			return null;
		}
	}
	public String valid(Map<String, String[]> map) throws AlipayApiException {
		Map<String, String> params = new HashMap<>(); 
	    for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();)
	    {
	      String name = iter.next();
	      String[] values = map.get(name);
	      String valueStr = "";
	      for (int i = 0; i < values.length; i++) {
	    	  valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ","; 
	      } 
	      params.put(name, valueStr);
	    }
	    LogHelper.log(params.toString());
	    try {
	    	if (AlipaySignature.rsaCheckV1(params, publicKey, "utf-8", "RSA2")) {
	    		return params.get("out_trade_no");
	    	} else {
	    		LogHelper.log("验证没通过");
	    		return params.get("out_trade_no");
	    	}
	    } catch (AlipayApiException e) {
	    	LogHelper.log(e.getMessage());
	    	
	    }
	    if (params.get("trade_status").equals("TRADE_SUCCESS"))
	           return params.get("out_trade_no");
	    else return null;
	}
	
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<>(); 
		params.put("d", "456879");
		System.out.println(params.toString());
		
	}
}
