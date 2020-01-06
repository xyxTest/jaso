package com.yaj.common.pay.wechatpay;
 
public class WeChatModel {
	// key 生成  md5 用的是 wxsw20181212生成， 操作密码 ：wxsw20181212.
	  private String appid;
	  private String mch_id;
	  private String device_info = "WEB";
	  private String nonce_str;
	  private String sign;
	  private String sign_type = "MD5";
	  private String body = "工匠教育-商品购买";
	  private String detail;
	  private String attach;
	  private String out_trade_no;
	  private String fee_type = "CNY";
	  private Integer total_fee = 1;
	  private String spbill_create_ip;
	  private String time_start;
	  private String time_expire;
	  private String goods_tag;
	  private String notify_url = "https://rwyht.wh66.cn/member/charge/weixin/success";
	  private String trade_type = "APP";
	  private String product_id;
	  private String limit_pay;
	  private String openid;
	  private String scene_info;
	  
	  public String getAppid()
	  {
	    return this.appid;
	  }
	  
	  public void setAppid(String appid)
	  {
	    this.appid = appid;
	  }
	  
	  public String getMch_id()
	  {
	    return this.mch_id;
	  }
	  
	  public void setMch_id(String mch_id)
	  {
	    this.mch_id = mch_id;
	  }
	  
	  public String getDevice_info()
	  {
	    return this.device_info;
	  }
	  
	  public void setDevice_info(String device_info)
	  {
	    this.device_info = device_info;
	  }
	  
	  public String getNonce_str()
	  {
	    return this.nonce_str;
	  }
	  
	  public void setNonce_str(String nonce_str)
	  {
	    this.nonce_str = nonce_str;
	  }
	  
	  public String getSign()
	  {
	    return this.sign;
	  }
	  
	  public void setSign(String sign)
	  {
	    this.sign = sign;
	  }
	  
	  public String getSign_type()
	  {
	    return this.sign_type;
	  }
	  
	  public void setSign_type(String sign_type)
	  {
	    this.sign_type = sign_type;
	  }
	  
	  public String getBody()
	  {
	    return this.body;
	  }
	  
	  public void setBody(String body)
	  {
	    this.body = body;
	  }
	  
	  public String getDetail()
	  {
	    return this.detail;
	  }
	  
	  public void setDetail(String detail)
	  {
	    this.detail = detail;
	  }
	  
	  public String getAttach()
	  {
	    return this.attach;
	  }
	  
	  public void setAttach(String attach)
	  {
	    this.attach = attach;
	  }
	  
	  public String getOut_trade_no()
	  {
	    return this.out_trade_no;
	  }
	  
	  public void setOut_trade_no(String out_trade_no)
	  {
	    this.out_trade_no = out_trade_no;
	  }
	  
	  public String getFee_type()
	  {
	    return this.fee_type;
	  }
	  
	  public void setFee_type(String fee_type)
	  {
	    this.fee_type = fee_type;
	  }
	  
	  public int getTotal_fee()
	  {
	    return this.total_fee;
	  }
	  
	  public void setTotal_fee(int total_fee)
	  {
	    this.total_fee = total_fee;
	  }
	  
	  public String getSpbill_create_ip()
	  {
	    return this.spbill_create_ip;
	  }
	  
	  public void setSpbill_create_ip(String spbill_create_ip)
	  {
	    this.spbill_create_ip = spbill_create_ip;
	  }
	  
	  public String getTime_start()
	  {
	    return this.time_start;
	  }
	  
	  public void setTime_start(String time_start)
	  {
	    this.time_start = time_start;
	  }
	  
	  public String getTime_expire()
	  {
	    return this.time_expire;
	  }
	  
	  public void setTime_expire(String time_expire)
	  {
	    this.time_expire = time_expire;
	  }
	  
	  public String getGoods_tag()
	  {
	    return this.goods_tag;
	  }
	  
	  public void setGoods_tag(String goods_tag)
	  {
	    this.goods_tag = goods_tag;
	  }
	  
	  public String getNotify_url()
	  {
	    return this.notify_url;
	  }
	  
	  public void setNotify_url(String notify_url)
	  {
	    this.notify_url = notify_url;
	  }
	  
	  public String getTrade_type()
	  {
	    return this.trade_type;
	  }
	  
	  public void setTrade_type(String trade_type)
	  {
	    this.trade_type = trade_type;
	  }
	  
	  public String getProduct_id()
	  {
	    return this.product_id;
	  }
	  
	  public void setProduct_id(String product_id)
	  {
	    this.product_id = product_id;
	  }
	  
	  public String getLimit_pay()
	  {
	    return this.limit_pay;
	  }
	  
	  public void setLimit_pay(String limit_pay)
	  {
	    this.limit_pay = limit_pay;
	  }
	  
	  public String getOpenid()
	  {
	    return this.openid;
	  }
	  
	  public void setOpenid(String openid)
	  {
	    this.openid = openid;
	  }
	  
	  public String getScene_info()
	  {
	    return this.scene_info;
	  }
	  
	  public void setScene_info(String scene_info)
	  {
	    this.scene_info = scene_info;
	  }
	  
	  public String toKeyString()
	  {
	    StringBuffer sb = new StringBuffer();
	    sb.append("appid=" + this.appid);
	    if (this.attach != null) {
	      sb.append("&attach=" + this.attach);
	    }
	    if (this.body != null) {
	      sb.append("&body=" + this.body);
	    }
	    if (this.detail != null) {
	      sb.append("&detail=" + this.detail);
	    }
	    sb.append("&device_info=" + this.device_info);
	    sb.append("&fee_type=" + this.fee_type);
	    if (this.goods_tag != null) {
	      sb.append("&goods_tag=" + this.goods_tag);
	    }
	    if (this.limit_pay != null) {
	      sb.append("&limit_pay=" + this.limit_pay);
	    }
	    sb.append("&mch_id=" + this.mch_id);
	    if (this.nonce_str != null) {
	      sb.append("&nonce_str=" + this.nonce_str);
	    }
	    sb.append("&notify_url=" + this.notify_url);
	    if (this.openid != null) {
	      sb.append("&openid=" + this.openid);
	    }
	    sb.append("&out_trade_no=" + this.out_trade_no);
	    if (this.product_id != null) {
	      sb.append("&product_id=" + this.product_id);
	    }
	    if (this.scene_info != null) {
	      sb.append("&scene_info=" + this.scene_info);
	    }
	    sb.append("&sign_type=" + this.sign_type);
	    if (this.spbill_create_ip != null) {
	      sb.append("&spbill_create_ip=" + this.spbill_create_ip);
	    }
	    if (this.time_expire != null) {
	      sb.append("&time_expire=" + this.time_expire);
	    }
	    if (this.time_start != null) {
	      sb.append("&time_start=" + this.time_start);
	    }
	    sb.append("&total_fee=" + this.total_fee);
	    if (this.trade_type != null) {
	      sb.append("&trade_type=" + this.trade_type);
	    }
	    return sb.toString();
	  }
}
