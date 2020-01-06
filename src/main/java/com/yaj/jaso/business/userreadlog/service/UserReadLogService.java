package com.yaj.jaso.business.userreadlog.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.userreadlog.entity.po.UserReadLogPO;
import com.yaj.jaso.business.userreadlog.mapper.UserReadLogMapper;

/*
 * @Description: 
 * @date: 2019-09-09
 */
@Service
public class UserReadLogService extends ServiceImpl<UserReadLogMapper, UserReadLogPO> {

    @Resource
    UserReadLogMapper userReadLogMapper;

}
