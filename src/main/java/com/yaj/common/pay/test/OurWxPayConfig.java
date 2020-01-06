package com.yaj.common.pay.test;

import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class OurWxPayConfig implements WXPayConfig{

	@Override
	public String getAppID() {
		// TODO Auto-generated method stub
		return "wx89aaee5dfa45f172";
	}

	@Override
	public InputStream getCertStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "50C8C5E16C0323A6F0D5B17EBF8EA70E";
	}

	@Override
	public String getMchID() {
		// TODO Auto-generated method stub
		return "1514846241";
	}

}
