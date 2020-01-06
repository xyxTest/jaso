package com.yaj.jaso.business.measurescreenlog.entity.vo;

import com.yaj.jaso.business.measurescreenlog.entity.po.MeasureScreenLogPO;

public class MeasureScreenLogResult extends MeasureScreenLogPO {
	private Integer doneNum;//已测
	private Integer allNum;//总点数
	private Integer problemNum;//爆点
	public Integer getDoneNum() {
		return doneNum;
	}
	public void setDoneNum(Integer doneNum) {
		this.doneNum = doneNum;
	}
	public Integer getAllNum() {
		return allNum;
	}
	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}
	public Integer getProblemNum() {
		return problemNum;
	}
	public void setProblemNum(Integer problemNum) {
		this.problemNum = problemNum;
	}
	
	
}
