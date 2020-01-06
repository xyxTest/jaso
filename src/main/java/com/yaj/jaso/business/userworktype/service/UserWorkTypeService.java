package com.yaj.jaso.business.userworktype.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.userworktype.entity.po.UserWorkTypePO;
import com.yaj.jaso.business.userworktype.mapper.UserWorkTypeMapper;

/*
 * @Description: 用户-工种关系表
 * @date: 2019-11-13
 */
@Service
public class UserWorkTypeService extends ServiceImpl<UserWorkTypeMapper, UserWorkTypePO> {

    @Resource
    UserWorkTypeMapper userWorkTypeMapper;

}
