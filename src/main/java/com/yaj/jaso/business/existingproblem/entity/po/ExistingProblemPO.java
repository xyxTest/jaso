package com.yaj.jaso.business.existingproblem.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@TableName(value = "existing_problem")
public class ExistingProblemPO {
    /*
    *
    */
    @TableId
    private Long existingProblemId;
    /*
    *
    */
    private String existingProblemName;
    /*
    *
    */
    private Long companyId;
    /*
    *项目id
    */
    private Long projectId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setExistingProblemId(Long existingProblemId) {
        this.existingProblemId = existingProblemId;
    }

    public Long getExistingProblemId() {
        return this.existingProblemId;
    }

    public void setExistingProblemName(String existingProblemName) {
        this.existingProblemName = existingProblemName;
    }

    public String getExistingProblemName() {
        return this.existingProblemName;
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

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}