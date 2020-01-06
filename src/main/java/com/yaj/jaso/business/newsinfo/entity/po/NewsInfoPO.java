package com.yaj.jaso.business.newsinfo.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-09
 */
@TableName(value = "news_info")
public class NewsInfoPO {
    /*
    *
    */
    @TableId
    private Long newsInfoId;
    /*
    *
    */
    private String content;
    /*
    *
    */
    private String remark;
    /*
    *主题
    */
    private String topic;
    /*
    *已读数量
    */
    private Integer readNum;
    /*
    *
    */
    private Date createTime;
    /*
    *1、一般 2、热门 3、置顶
    */
    private Integer readStatus;
    /*
    *
    */
    private Long companyId;
    
    private Long jasoUserId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setNewsInfoId(Long newsInfoId) {
        this.newsInfoId = newsInfoId;
    }

    public Long getNewsInfoId() {
        return this.newsInfoId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getReadNum() {
        return this.readNum;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getReadStatus() {
        return this.readStatus;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

}