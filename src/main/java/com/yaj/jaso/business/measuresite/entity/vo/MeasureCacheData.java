package com.yaj.jaso.business.measuresite.entity.vo;

import java.util.List;
import com.yaj.jaso.business.measurepaper.entity.po.MeasurePaperPO;
import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;

public class MeasureCacheData {
	private List<ProjectCheckTypePO> checkTypeList;//检查项的表
	private List<MeasurePaperPO> measurePaperList;//图纸表
	private List<PointAndCheckTypeList> pointList;//该项目下的所有测点
	private List<MeasureScreenList> measureFilterDataList;//实测实量过滤入口信息
	
	public List<ProjectCheckTypePO> getCheckTypeList() {
		return checkTypeList;
	}
	public void setCheckTypeList(List<ProjectCheckTypePO> checkTypeList) {
		this.checkTypeList = checkTypeList;
	}
	public List<MeasurePaperPO> getMeasurePaperList() {
		return measurePaperList;
	}
	public void setMeasurePaperList(List<MeasurePaperPO> measurePaperList) {
		this.measurePaperList = measurePaperList;
	}
	public List<PointAndCheckTypeList> getPointList() {
		return pointList;
	}
	public void setPointList(List<PointAndCheckTypeList> pointList) {
		this.pointList = pointList;
	}
	public List<MeasureScreenList> getMeasureFilterDataList() {
		return measureFilterDataList;
	}
	public void setMeasureFilterDataList(List<MeasureScreenList> measureFilterDataList) {
		this.measureFilterDataList = measureFilterDataList;
	}
	
}
