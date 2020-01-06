package com.yaj.jaso.business.studypractice.entity.vo;

import com.yaj.jaso.business.studyanswerrecord.entity.po.StudyAnswerRecordPO;

public class RecordModo extends StudyAnswerRecordPO{
	private Long studyPracticeId;

	public Long getStudyPracticeId() {
		return studyPracticeId;
	}

	public void setStudyPracticeId(Long studyPracticeId) {
		this.studyPracticeId = studyPracticeId;
	}
}
