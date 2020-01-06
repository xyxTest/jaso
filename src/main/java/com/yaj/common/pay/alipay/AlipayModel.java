package com.yaj.common.pay.alipay;

import java.math.BigDecimal;

public class AlipayModel {
	// 商户订单号，商户网站订单系统中唯一订单号，必填
	private String orderNo;
	// 订单名称，必填
	private String orderTitle;
	// 付款金额，必填
	private BigDecimal orderPrice;
	
	private String orderBody;
	// 销售产品码 必填
	private String productCode;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderBody() {
		return orderBody;
	}
	public void setOrderBody(String orderBody) {
		this.orderBody = orderBody;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	
	
}
