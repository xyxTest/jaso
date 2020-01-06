package com.yaj.jaso.business.workdepartmentrule.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.workdepartmentrule.entity.po.WorkDepartmentRulePO;
import com.yaj.jaso.business.workdepartmentrule.mapper.WorkDepartmentRuleMapper;

/*
 * @Description: 
 * @date: 2019-12-04
 */
@Service
public class WorkDepartmentRuleService extends ServiceImpl<WorkDepartmentRuleMapper, WorkDepartmentRulePO> {

    @Resource
    WorkDepartmentRuleMapper workDepartmentRuleMapper;

}
