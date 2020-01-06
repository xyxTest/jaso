package com.yaj.jaso.business.qualitycheckusers.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.qualitycheckusers.entity.po.QualityCheckUsersPO;
import com.yaj.jaso.business.qualitycheckusers.mapper.QualityCheckUsersMapper;

/*
 * @Description: 
 * @date: 2019-09-05
 */
@Service
public class QualityCheckUsersService extends ServiceImpl<QualityCheckUsersMapper, QualityCheckUsersPO> {

    @Resource
    QualityCheckUsersMapper qualityCheckUsersMapper;

}
