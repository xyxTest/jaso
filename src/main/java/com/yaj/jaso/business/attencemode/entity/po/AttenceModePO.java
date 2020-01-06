package com.yaj.jaso.business.attencemode.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 考勤模板（上班打卡时间、下班打卡时间、打卡有效范围、打卡地点）
 * @date: 2019-11-05
 */
@TableName(value = "attence_mode")
public class AttenceModePO {
    /*
    *
    */
    @TableId
    private Long attenceModeId;
    /*
    *
    */
    private Long attencePlaceId;
    /*
    *打卡范围
    */
    private Integer attenceRange;
    /*
    *上班时间
    */
    private String workTime;//eg:8:30
    /*
    *下班时间
    */
    private String closingTime;//eg:18:00
    /*
    *休息日（星期六、星期日）
    */
    private String holiday;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setAttenceModeId(Long attenceModeId) {
        this.attenceModeId = attenceModeId;
    }

    public Long getAttenceModeId() {
        return this.attenceModeId;
    }

    public void setAttenceRange(Integer attenceRange) {
        this.attenceRange = attenceRange;
    }

    public Integer getAttenceRange() {
        return this.attenceRange;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkTime() {
        return this.workTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getClosingTime() {
        return this.closingTime;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getHoliday() {
        return this.holiday;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public Long getAttencePlaceId() {
		return attencePlaceId;
	}

	public void setAttencePlaceId(Long attencePlaceId) {
		this.attencePlaceId = attencePlaceId;
	}

}