package com.yaj.jaso.business.craftsman.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 工匠身份证号唯一
 * @date: 2020-01-03
 */
@TableName(value = "craftsman")
public class CraftsmanPO {
    /*
    *
    */
    @TableId
    private Long craftsmanId;
    /*
    *班组
    */
    private Long projectTeamId;
    /*
    *工种
    */
    private Long workTypeId;
    /*
    *用户id
    */
    private Long jasoUserId;
    /*
    *姓名
    */
    private String realName;
    /*
    *手机号
    */
    private String tel;
    /*
    *年龄
    */
    private Integer age;
    /*
    *身份证号
    */
    private String idCard;
    /*
    *性别（0，男  1、女）
    */
    private Integer sex;
    /*
    *日工资
    */
    private Integer daySalary;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setCraftsmanId(Long craftsmanId) {
        this.craftsmanId = craftsmanId;
    }

    public Long getCraftsmanId() {
        return this.craftsmanId;
    }

    public void setProjectTeamId(Long projectTeamId) {
        this.projectTeamId = projectTeamId;
    }

    public Long getProjectTeamId() {
        return this.projectTeamId;
    }

    public void setWorkTypeId(Long workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Long getWorkTypeId() {
        return this.workTypeId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return this.tel;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setDaySalary(Integer daySalary) {
        this.daySalary = daySalary;
    }

    public Integer getDaySalary() {
        return this.daySalary;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}