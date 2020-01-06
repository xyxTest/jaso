package com.yaj.jaso.business.securitycheck.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-07
 */
@TableName(value = "security_check")
public class SecurityCheckPO {
    /*
    *
    */
    @TableId
    private Long securityCheckId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long projectId;
    /*
     *检查名称 
     */
    private String securityCheckName;
    /*
    *整改单截止日期
    */
    private Date finishedDate;
    /*
    *整改单起始日期（即指派日期）
    */
    private Date startDate;
    /*
    *(1、通过  2、不通过)
    */
    private Integer type;
    /*
    *(1、待指派  2、待接受 3、进行中 4、待验收 5、已完成)
    */
    private Integer status;
    /*
     *进度 
     **/
    private Integer process;
    /*
    *评分
    */
    private Integer score;
    /*
    *(1、一般 2、重要 3、紧急)
    */
    private Integer state;
    /*
    *
    */
    private Date createTime;
    /*
    *通知人
    */
    private String informUsers;
    /*
    *
    */
    private Long projectPaperId;
    /*
    *图纸位置x坐标
    */
    private Integer x;
    /*
    *图纸位置y坐标
    */
    private Integer y;
    /*
    *创建人
    */
    private Long jasoUserId;
    /*
    *性质id
    */
    private Long natureId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setSecurityCheckId(Long securityCheckId) {
        this.securityCheckId = securityCheckId;
    }

    public Long getSecurityCheckId() {
        return this.securityCheckId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public Date getFinishedDate() {
        return this.finishedDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setInformUsers(String informUsers) {
        this.informUsers = informUsers;
    }

    public String getInformUsers() {
        return this.informUsers;
    }

    public void setProjectPaperId(Long projectPaperId) {
        this.projectPaperId = projectPaperId;
    }

    public Long getProjectPaperId() {
        return this.projectPaperId;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getX() {
        return this.x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getY() {
        return this.y;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setNatureId(Long natureId) {
        this.natureId = natureId;
    }

    public Long getNatureId() {
        return this.natureId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public String getSecurityCheckName() {
		return securityCheckName;
	}

	public void setSecurityCheckName(String securityCheckName) {
		this.securityCheckName = securityCheckName;
	}

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}