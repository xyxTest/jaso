package com.yaj.jaso.business.userdepartment.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.userdepartment.entity.po.UserDepartmentPO;
import com.yaj.jaso.business.userdepartment.mapper.UserDepartmentMapper;

/*
 * @Description: 
 * @date: 2019-07-31
 */
@Service
public class UserDepartmentService extends ServiceImpl<UserDepartmentMapper, UserDepartmentPO> {

    @Resource
    UserDepartmentMapper userDepartmentMapper;

}
