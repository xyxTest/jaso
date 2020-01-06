package com.yaj.jaso.business.studydata.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.studyanswerrecord.entity.po.StudyAnswerRecordPO;
import com.yaj.jaso.business.studydata.entity.po.StudyDataPO;
import com.yaj.jaso.business.studydata.entity.vo.ImportStudyData;
import com.yaj.jaso.business.studydata.entity.vo.PaperInport;
import com.yaj.jaso.business.studydata.entity.vo.SelectOption;
import com.yaj.jaso.business.studydata.entity.vo.StudyDataVo;
import com.yaj.jaso.business.studydata.mapper.StudyDataMapper;
import com.yaj.jaso.business.studyworkertype.entity.po.StudyWorkerTypePO;
import com.yaj.jaso.business.userintegrallog.entity.po.UserIntegralLogPO;
import com.yaj.jaso.business.userintegrallog.service.UserIntegralLogService;
import com.yaj.xyx.util.MD5Util;

/*
 * @Description: 题目列表
 * @date: 2019-10-24
 */
@Service
public class StudyDataService extends ServiceImpl<StudyDataMapper, StudyDataPO> {

    @Resource
    StudyDataMapper studyDataMapper;
    @Autowired
    ServiceMain serviceMain;
    @Autowired 
    UserIntegralLogService logService;
    public Object add(StudyDataPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<StudyDataPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(StudyDataVo po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.name", new StudyDataPO(),new StudyWorkerTypePO());
		mul.setPage(po.getPageVo().getPageNo(), po.getPageVo().getPageSize());
		mul.where("${study_data}")
		.eq("companyId", cacheUser.getCompanyId())
		.eq(po.getStudyWorkerTypeId()!=null,"studyWorkerTypeId", po.getStudyWorkerTypeId())
		.like(po.getDataName()!=null,"dataName", po.getDataName());
		mul.setOrderBy("studydata.create_time desc");
		return serviceMain.mulSelect(mul);
	}

	public Object selectLists(PaperInport po) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Long> ids = new ArrayList<>();
		if(po.getStudyDataList()!=null && !po.getStudyDataList().equals("")){
			String[] strs = po.getStudyDataList().split(",");
			for(String item:strs){
				ids.add(Long.valueOf(item));
			}
		}
		MulSelect mul = MulSelect.newInstance("${1}.name", new StudyDataPO(),new StudyWorkerTypePO());
		mul.where("${study_data}")
		.eq("companyId", cacheUser.getCompanyId())
		.eq(po.getStudyWorkerTypeId()!=null,"studyWorkerTypeId", po.getStudyWorkerTypeId())
		.in(!ids.isEmpty(), "studyDataId", ids);
		return serviceMain.mulSelect(mul);
	}

	public Object importStudyData(MultipartFile file, HttpServletRequest request,Long studyWorkerTypeId) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<ImportStudyData> ms = new ArrayList<ImportStudyData>();
		ReadStudyDataExcel rm = new ReadStudyDataExcel();
		String newFileName = MD5Util.getMD5String(file.getOriginalFilename() + new Date() + UUID.randomUUID().toString()).replace(".","")
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		ms=rm.getExcelInfo(newFileName, file);
		List<StudyDataPO> polist = new ArrayList<StudyDataPO>();
		for(int j=0;j<ms.size();j++){
				StudyDataPO m = new StudyDataPO();
				m.setCompanyId(cacheUser.getCompanyId());
				m.setDataName(ms.get(j).getName());
				m.setStudyDataOptions(ms.get(j).getStudyDataOptions());
				m.setRightKey(ms.get(j).getRightKey());
				m.setAnswerAnalysis(ms.get(j).getAnswerAnalysis());
				m.setStudyWorkerTypeId(studyWorkerTypeId);
				m.setDifficultyLevel(1);
				polist.add(m);
		}
		return insertOrUpdateBatch(polist);
	}

	public Object getStudyPaperList(SelectOption po) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<StudyDataPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", cacheUser.getCompanyId())
		.eq(po.getStudyWorkerTypeId()!=null,"study_worker_type_id", po.getStudyWorkerTypeId())
		.in(!po.getIds().isEmpty(),"study_data_id", po.getIds());
		return selectList(wrapper);
	}

	public Object selectMainList(StudyDataPO po) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<StudyDataPO> dataList = new ArrayList<>();
		Wrapper<StudyDataPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", cacheUser.getCompanyId())
		.eq(po.getStudyWorkerTypeId()!=null,"study_worker_type_id", po.getStudyWorkerTypeId());
		dataList = selectList(wrapper);
		List<Long> ids = new ArrayList<>();
		if(dataList.size()>=3){
			List<Integer> list = new ArrayList<>();
			while(list.size()<=3){
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
			for(int j=0;j<list.size();j++){
				ids.add(dataList.get(list.get(j)).getStudyDataId());
			}
		}else{
			return "题库数量不够！";
		}
		MulSelect mul = MulSelect.newInstance("${1}.name", new StudyDataPO(),new StudyWorkerTypePO());
		mul.where("${study_data}")
		.eq("companyId", cacheUser.getCompanyId())
		.in(!ids.isEmpty(), "studyDataId", ids);
		return serviceMain.mulSelect(mul);
	}

	public Object submitPaper(List<StudyAnswerRecordPO> recordList) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		//DecimalFormat df = new DecimalFormat( "0.00"); //设置double类型小数点后位数格式  
		double percent =0;
		if(!recordList.isEmpty()){
			int rightNum=0;
			for(int i=0;i<recordList.size();i++){
				recordList.get(i).setCompanyId(cacheUser.getCompanyId());
				if(recordList.get(i).getIsRight()==1){
					rightNum++;
				}
			}
			percent= (double)rightNum/(double)recordList.get(0).getDataNum();
			///设置用户做题积分记录，每天一次一分，封顶一天六次
			java.util.Date date=new java.util.Date();
	        java.sql.Date sdate=new java.sql.Date(date.getTime());
	        Wrapper<UserIntegralLogPO> logWrapper = new EntityWrapper<>();
	        logWrapper.eq("company_id", cacheUser.getCompanyId())
	        .eq("jaso_user_id", cacheUser.getJasoUserId())
	        .eq("create_time", sdate)
	        .eq("type", 5);
	        if(logService.selectList(logWrapper).size()<2){
	        	UserIntegralLogPO log = new UserIntegralLogPO();
				log.setCompanyId(cacheUser.getCompanyId());
				log.setJasoUserId(cacheUser.getJasoUserId());
				if(recordList.get(0).getDataNum()!=null){
					if(percent>0.8){
						log.setNum(3);
					}else{
						log.setNum(1);
					}
				}else{
					log.setNum(1);
				}
				log.setType(5);
				log.setCreateTime(sdate);
				logService.insertOrUpdate(log);
				return ((int)(percent*100));
	        }
		}
		return ((int)(percent*100));
	}

}
