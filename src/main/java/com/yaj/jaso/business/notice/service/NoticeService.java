package com.yaj.jaso.business.notice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.service.JasoUserService;
import com.yaj.jaso.business.notice.entity.po.NoticePO;
import com.yaj.jaso.business.notice.entity.vo.NoticePush;
import com.yaj.jaso.business.notice.entity.vo.NoticeVo;
import com.yaj.jaso.business.notice.mapper.NoticeMapper;
import com.yaj.jaso.global.PageVoUtil;
import com.yaj.xyx.util.JSMSExample;

/*
 * @Description: 
 * @date: 2019-11-06
 */
@Service
public class NoticeService extends ServiceImpl<NoticeMapper, NoticePO> {

    @Resource
    NoticeMapper noticeMapper;
    @Autowired
    JasoUserService userService;
	public Object add(NoticePush po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		NoticePO no = new NoticePO();
		no.setCompanyId(userInCache.getCompanyId());
		no.setContent(po.getContent());
		no.setTitle(po.getTitle());
		if(insertOrUpdate(no)){
			if(po.getUserIdList()!=null && !po.getUserIdList().isEmpty()){
				List<JasoUserPO> userList = new ArrayList<>();
				Wrapper<JasoUserPO> wrapper = new EntityWrapper<>();
				wrapper.eq("company_id", userInCache.getCompanyId())
				.in("jaso_user_id", po.getUserIdList());
				userList=userService.selectList(wrapper);
				//手机号码批量推送
				JSMSExample.testSendBatchTemplateSMS(userList,po);
			}
		}else{
			return false;
		}
		return true;
	}

	public Object deleteBatchByIds(List<NoticePO> po) {
		// TODO Auto-generated method stub
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(NoticeVo po) {
		// TODO Auto-generated method stub
		Page<NoticePO> page = new Page<NoticePO>();
		page.setCurrent(po.getPageVo().getPageNo()); 
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<NoticePO> wrapper = new EntityWrapper<>();
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

}
