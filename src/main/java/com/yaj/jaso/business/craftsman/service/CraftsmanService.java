package com.yaj.jaso.business.craftsman.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.craftsman.entity.po.CraftsmanPO;
import com.yaj.jaso.business.craftsman.mapper.CraftsmanMapper;

/*
 * @Description: 工匠身份证号唯一
 * @date: 2020-01-03
 */
@Service
public class CraftsmanService extends ServiceImpl<CraftsmanMapper, CraftsmanPO> {

    @Resource
    CraftsmanMapper craftsmanMapper;

}
