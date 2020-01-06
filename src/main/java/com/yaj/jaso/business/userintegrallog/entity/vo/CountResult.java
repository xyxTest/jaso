package com.yaj.jaso.business.userintegrallog.entity.vo;

import java.util.List;

public class CountResult {
	private Integer historyNum;//总积分
	private Integer todayNum;//当天总积分
	private List<ResultList> resultList;
	public Integer getHistoryNum() {
		return historyNum;
	}
	public void setHistoryNum(Integer historyNum) {
		this.historyNum = historyNum;
	}
	public Integer getTodayNum() {
		return todayNum;
	}
	public void setTodayNum(Integer todayNum) {
		this.todayNum = todayNum;
	}
	public List<ResultList> getResultList() {
		return resultList;
	}
	public void setResultList(List<ResultList> resultList) {
		this.resultList = resultList;
	}
	
}
