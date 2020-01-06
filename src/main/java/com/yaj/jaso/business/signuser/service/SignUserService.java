package com.yaj.jaso.business.signuser.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.signuser.entity.po.SignUserPO;
import com.yaj.jaso.business.signuser.mapper.SignUserMapper;

/*
 * @Description: 
 * @date: 2019-10-22
 */
@Service
public class SignUserService extends ServiceImpl<SignUserMapper, SignUserPO> {

    @Resource
    SignUserMapper signUserMapper;

}
