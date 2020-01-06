package com.yaj.jaso.business.message.entity.vo;

import java.util.Date;

public class GetResult {
	 private Long messageId;
	    /*
	    *
	    */
	    private Long companyId;
	    /*
	    *接收人
	    */
	    private Long jasoUserId;
	    /*
	     *接收人
	     */
	     private Long createUserId;
	    /**
	     *标题 
	     */
	    private String title;
	    /**
	     *内容 
	     */
	    private String content;
	    /*
	    *
	    */
	    private Long aboutId;
	    /*
	    *1、质量 2、安全 3、实测实量 4、工作
	    */
	    private Integer type;
	    /*
	    *0、未读 1、已读
	    */
	    private Integer state;
	    /*
	    *
	    */
	    private Date createTime;
	    /*
	    *
	    */
	    private Integer ifDelete;
	    private String userIcon;

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAboutId() {
		return aboutId;
	}

	public void setAboutId(Long aboutId) {
		this.aboutId = aboutId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}
	
}
