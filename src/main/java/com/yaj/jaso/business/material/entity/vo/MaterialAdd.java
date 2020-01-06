package com.yaj.jaso.business.material.entity.vo;

import com.yaj.jaso.business.material.entity.po.MaterialPO;

public class MaterialAdd extends MaterialPO{
	private Integer currentInputNum;

	public Integer getCurrentInputNum() {
		return currentInputNum;
	}

	public void setCurrentInputNum(Integer currentInputNum) {
		this.currentInputNum = currentInputNum;
	}
}
