package com.yaj.jaso.business.qualitycheck.entity.vo;

import java.util.List;

import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;

public class QualityCheckSend extends QualityCheckPO{
	private List<AboutUserList> rectifyUserList;//整改人
	private List<AboutUserList> participantsList;//参与人
	public List<AboutUserList> getRectifyUserList() {
		return rectifyUserList;
	}
	public void setRectifyUserList(List<AboutUserList> rectifyUserList) {
		this.rectifyUserList = rectifyUserList;
	}
	public List<AboutUserList> getParticipantsList() {
		return participantsList;
	}
	public void setParticipantsList(List<AboutUserList> participantsList) {
		this.participantsList = participantsList;
	}
}
