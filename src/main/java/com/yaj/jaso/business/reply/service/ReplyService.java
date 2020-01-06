package com.yaj.jaso.business.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.reply.entity.po.ReplyPO;
import com.yaj.jaso.business.reply.entity.vo.ReplyVo;
import com.yaj.jaso.business.reply.mapper.ReplyMapper;

/*
 * @Description: 
 * @date: 2019-08-28
 */
@Service
public class ReplyService extends ServiceImpl<ReplyMapper, ReplyPO> {

    @Resource
    ReplyMapper replyMapper;
    @Autowired
    ServiceMain serviceMain;
	public Object add(ReplyPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		po.setJasoUserId(userInCache.getJasoUserId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<ReplyPO> po) {
		// TODO Auto-generated method stub
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectByPage(ReplyVo po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${1}.userIcon", new ReplyPO(), new JasoUserPO());
		//mul.setPage(po.getPageVo().getPageNo(), po.getPageVo().getPageSize());
		mul.where("${reply}")
			//.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", cacheUser.getCompanyId())
			.eq(po.getReplyType()!=null,"replyType",po.getReplyType())
			.eq(po.getAboutId()!=null,"aboutId",po.getAboutId());
		mul.setOrderBy("reply.reply_id");
        return serviceMain.mulSelect(mul);
	}

}
