package com.yaj.jaso.business.pointcheck.entity.vo;

import java.util.List;

import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;
import com.yaj.jaso.business.pointdatainputlog.entity.po.PointDataInputLogPO;

public class PointCheckInput extends PointCheckPO {
	private List<PointDataInputLogPO> dataLogList;

	public List<PointDataInputLogPO> getDataLogList() {
		return dataLogList;
	}

	public void setDataLogList(List<PointDataInputLogPO> dataLogList) {
		this.dataLogList = dataLogList;
	}
}
