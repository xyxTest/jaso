package com.yaj.jaso.business.projectadresscode.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.projectadresscode.entity.po.ProjectAdressCodePO;
import com.yaj.jaso.business.projectadresscode.mapper.ProjectAdressCodeMapper;

/*
 * @Description: 
 * @date: 2019-08-13
 */
@Service
public class ProjectAdressCodeService extends ServiceImpl<ProjectAdressCodeMapper, ProjectAdressCodePO> {

    @Resource
    ProjectAdressCodeMapper projectAdressCodeMapper;

}
