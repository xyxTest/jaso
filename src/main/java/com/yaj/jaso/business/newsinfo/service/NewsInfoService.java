package com.yaj.jaso.business.newsinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.newsinfo.entity.po.NewsInfoPO;
import com.yaj.jaso.business.newsinfo.entity.vo.NewsInfoVo;
import com.yaj.jaso.business.newsinfo.mapper.NewsInfoMapper;
import com.yaj.xyx.util.GetWebPosition;

/*
 * @Description: 
 * @date: 2019-09-09
 */
@Service
public class NewsInfoService extends ServiceImpl<NewsInfoMapper, NewsInfoPO> {

    @Resource
    NewsInfoMapper newsInfoMapper;
    @Autowired
    ServiceMain serviceMain;
    public Object add(NewsInfoPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setJasoUserId(userInCache.getJasoUserId());
		po.setCompanyId(userInCache.getCompanyId());
		po.setCreateTime(new Date());
		if(!insertOrUpdate(po)){
			return false;
		}
		/*new Thread(){
			public void run(){
				updateNews(po);
			}
		}.start();*/
		return true;
	}
    
    synchronized public Object updateNews(NewsInfoPO po){
    	GetWebPosition getWeb = new GetWebPosition();
    	boolean flag=false;
    	if(po.getNewsInfoId()!=null){
    		po.setRemark(getWeb.GetDataByTwo(po.getContent()));
			flag=insertOrUpdate(po);
			System.out.println("update over");
    		
    	}
    	return flag;
    }
	public Object deleteLists(List<NewsInfoPO> pos) {
		for(int i=0;i<pos.size();i++){
			pos.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(pos);
	}

	public Object selectPageList(NewsInfoVo pos) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul2 = MulSelect.newInstance("${1}.userRealName", new NewsInfoPO(),new JasoUserPO());
		mul2.setPage(pos.getPageVo().getPageNo(), pos.getPageVo().getPageSize());
		mul2.where("${news_info}")
			.eq("companyId", userInCache.getCompanyId())
			.like(pos.getContent()!=null,"content", pos.getContent());
		mul2.setOrderBy("newsinfo.read_status desc,newsinfo.read_num desc,newsinfo.create_time desc");
        return serviceMain.mulSelect(mul2);
	}

	public Object update(NewsInfoPO po) {
		NewsInfoPO newsinfo= new NewsInfoPO();
		newsinfo=selectById(po.getNewsInfoId());
		newsinfo.setReadNum(newsinfo.getReadNum()+1);
		if(newsinfo.getReadNum()>=50){
			newsinfo.setReadStatus(2);
		}
		return insertOrUpdate(newsinfo);
	}
}
