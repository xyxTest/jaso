package com.yaj.jaso.business.studydata.entity.vo;

public class ImportStudyData {
	private String name;
    /*
    *所有选项
    */
    private String studyDataOptions;
    /**
     *正确答案 
     */
    private String rightKey;
    /*
    *答案解析
    */
    private String answerAnalysis;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudyDataOptions() {
		return studyDataOptions;
	}
	public void setStudyDataOptions(String studyDataOptions) {
		this.studyDataOptions = studyDataOptions;
	}
	public String getRightKey() {
		return rightKey;
	}
	public void setRightKey(String rightKey) {
		this.rightKey = rightKey;
	}
	public String getAnswerAnalysis() {
		return answerAnalysis;
	}
	public void setAnswerAnalysis(String answerAnalysis) {
		this.answerAnalysis = answerAnalysis;
	}
    
}
