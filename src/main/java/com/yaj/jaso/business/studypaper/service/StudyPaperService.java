package com.yaj.jaso.business.studypaper.service;

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
import com.yaj.jaso.business.studydata.entity.po.StudyDataPO;
import com.yaj.jaso.business.studydata.service.StudyDataService;
import com.yaj.jaso.business.studypaper.entity.po.StudyPaperPO;
import com.yaj.jaso.business.studypaper.entity.vo.StudyPaperVo;
import com.yaj.jaso.business.studypaper.mapper.StudyPaperMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-11-01
 */
@Service
public class StudyPaperService extends ServiceImpl<StudyPaperMapper, StudyPaperPO> {

    @Resource
    StudyPaperMapper studyPaperMapper;
    @Autowired
    StudyDataService dataService;
	public Object add(StudyPaperPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache =(JasoUserPO) ThreadlocalManager.getThreadContext().getCurUser();
		List<StudyDataPO> dataList = new ArrayList<>();
		Wrapper<StudyDataPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId())
		.eq("study_worker_type_id", po.getStudyWorkerTypeId());
		dataList = dataService.selectList(wrapper);
		int size=20;
		if(po.getDataNum()!=null){
			size=po.getDataNum();
		}
		if(dataList.size()>=size){
			List<Integer> list = new ArrayList<>();
			while(list.size()<size){
				boolean flag=true;
				int num=(int)(Math.random() * (dataList.size()+1));
				for(int i=0;i<list.size();i++){
					if(list.get(i)==num){
						flag=false;
					}
				}
				if(flag){
					list.add(num);
				}
			}
			String str=list.get(0).toString();
			for(int j=0;j<list.size();j++){
				str=str+","+dataList.get(list.get(j)).getStudyDataId();
			}
			po.setStudyDataList(str);
			po.setCompanyId(userInCache.getCompanyId());
		}else{
			return "题库数量不够！";
		}
		if(po.getStudyDataList()!=null){
			return insertOrUpdate(po);
		}
		return false;
	}

	public Object deleteBatchByIds(List<StudyPaperPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(StudyPaperVo po) {
		JasoUserPO userInCache =(JasoUserPO) ThreadlocalManager.getThreadContext().getCurUser();
		Page<StudyPaperPO> page = new Page<StudyPaperPO>();
		page.setCurrent(po.getPageVo().getPageNo()); 
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<StudyPaperPO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getStudyWorkerTypeId()!=null,"study_worker_type_id", po.getStudyWorkerTypeId())
		.eq("company_id", userInCache.getCompanyId());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public Object selectLists(StudyPaperPO po) {
		JasoUserPO userInCache =(JasoUserPO) ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<StudyPaperPO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getStudyWorkerTypeId()!=null,"study_worker_type_id", po.getStudyWorkerTypeId())
		.eq("company_id", userInCache.getCompanyId());
		wrapper.orderDesc(Arrays.asList("create_time"));
		return selectList(wrapper);
	}


}
