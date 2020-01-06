package com.yaj.jaso.business.workposition.ctrl;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;  
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.pojo.PageVo;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.department.entity.po.DepartmentPO;
import com.yaj.jaso.business.department.service.DepartmentService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.userdepartment.entity.po.UserDepartmentPO;
import com.yaj.jaso.business.userdepartment.service.UserDepartmentService;
import com.yaj.jaso.business.workdepartmentrule.entity.po.WorkDepartmentRulePO;
import com.yaj.jaso.business.workdepartmentrule.service.WorkDepartmentRuleService;
import com.yaj.jaso.business.workposition.entity.po.WorkPositionPO; 
import com.yaj.jaso.business.workposition.entity.vo.Params;
import com.yaj.jaso.business.workposition.entity.vo.RecordsParam;
import com.yaj.jaso.business.workposition.service.WorkPositionService;
import com.yaj.jaso.business.workrecord.entity.po.WorkRecordPO;
import com.yaj.jaso.business.workrecord.service.WorkRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="",tags="")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="WorkPosition")
public class WorkPositionController {
	@Resource
	private WorkPositionService workPositionService;
	@Resource
	private WorkRecordService workRecordService;
	@Resource
	private ServiceMain service;
	@Resource
	private DepartmentService departmentServcie;
	@Resource
	private UserDepartmentService userDepartmentService;
	@Resource
	private WorkDepartmentRuleService workDepartmentRuleService;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="添加")
	@RequestMapping(value="/insert", method = RequestMethod.POST)
    public Object insert(
    		@RequestBody WorkPositionPO po) {
		po.setCompanyId(((JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser()).getCompanyId());
		
        return workPositionService.insertOrUpdate(po);
    }
	private static class Vo extends WorkPositionPO {
		private PageVo page;

		public PageVo getPage() {
			return page;
		} 
		public void setPage(PageVo page) {
			this.page = page;
		}
		
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="查找")
	@RequestMapping(value="/select", method = RequestMethod.POST)
    public Object select(
    		@RequestBody Vo vo) {
		WorkPositionPO workPosition = new WorkPositionPO();
		BeanUtil.copy(vo, workPosition);
		
		MulSelect mulSelect = MulSelect.newInstance("${1}", workPosition, new WorkDepartmentRulePO());
		mulSelect.where("0").eq("ifDelete", 0);
		if (vo.getPage() != null) {
			mulSelect.setPage(vo.getPage().getPageNo(), vo.getPage().getPageSize());
		}
        return service.mulSelect(mulSelect);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="删除")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    public Object delete(
    		@RequestBody WorkPositionPO workPosition) {
	String a = null;
		 return workPositionService.deleteById(workPosition.getPositionId()); 
    }
	
	private boolean coordinateToDistance(WorkPositionPO workPosition1, WorkPositionPO workPosition2) {
		double latitude1 = workPosition1.getPositionLatitude().doubleValue();
		double longitude1 = workPosition1.getPositionLongitude().doubleValue();
		
		double latitude2 = workPosition2.getPositionLatitude().doubleValue();
		double longitude2 = workPosition2.getPositionLongitude().doubleValue();
		
	    double a = latitude1 * Math.PI / 180.0 - latitude2 * Math.PI / 180.0;
	    double b = longitude1 * Math.PI / 180.0 - longitude2 * Math.PI / 180.0;
	    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
	            + Math.cos(latitude1 * Math.PI / 180.0)
	            * Math.cos(latitude2 * Math.PI / 180.0)
	            * Math.pow(Math.sin(b / 2), 2)));
	    s = s * 6378.137 * 1000;
	    s = Math.round(s * 10000) / 10000;
	    
	    if (s < workPosition1.getPositionRange()) {
	        return true;
	    }
	    return false;
	}   
	private WorkPositionPO punchTest(List<WorkPositionPO> list, WorkPositionPO ping) {
		for (WorkPositionPO item: list) {
			if (coordinateToDistance(item, ping))
				return item;
		}
		return null;
	}
	
	private void isLate(WorkRecordPO workRecord, WorkPositionPO workPosition) {
		final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		String now = sdf.format(new Date());
		
		if (workRecord.getWorkRecordType().equals(1) && now.compareTo(workPosition.getWorkStartTime()) < 0) {
			workRecord.setWorkLate(1);
		}
	}
	private void isLeaveEarly(WorkRecordPO workRecord, WorkPositionPO workPosition) {
		final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		String now = sdf.format(new Date());
		
		if (workRecord.getWorkRecordType().equals(2) && now.compareTo(workPosition.getWorkEndTime()) < 0) {
			workRecord.setWorkLeaveEarly(1);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="打卡")
	@RequestMapping(value="/punch", method = RequestMethod.POST)
    public Object punch(
    		@RequestBody Params params) {
		Long companyId = ((JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser()).getCompanyId();
		
		WorkPositionPO _po = new WorkPositionPO();
			_po.setCompanyId(companyId);
		Wrapper<WorkPositionPO> ew = new EntityWrapper<>(_po);	
		List<WorkPositionPO> list = workPositionService.selectList(ew);
		WorkPositionPO po = new WorkPositionPO();
		BeanUtil.copy(params, po);
		WorkPositionPO choose = punchTest(list, po);
		if (choose != null) {
			WorkRecordPO record = new WorkRecordPO();
				record.setWorkRecordType(params.getWorkRecordType());
				record.setJasoUserId(((JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser()).getJasoUserId());
				record.setPositionId(choose.getPositionId());
				record.setWorkRecordTime(new Date());
				isLate(record, choose);
				isLeaveEarly(record, choose);
			workRecordService.insert(record);
			return 1;
		} else {
			return -1;
		} 
    }
	private DepartmentPO getDepartment() { 
		JasoUserPO jasoUser = new JasoUserPO();
			jasoUser.setJasoUserId(((JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser()).getJasoUserId());
		MulSelect mulSelect = MulSelect.newInstance("${2}", jasoUser , new UserDepartmentPO(), new DepartmentPO()); 
		mulSelect.where("0").eq("ifDelete", 0);
		List<WorkDepartmentRulePO> rules = workDepartmentRuleService.selectList(new EntityWrapper<>());
			List<Map<String, Object>> rule = (List) service.mulSelect(mulSelect);
		if (rule.isEmpty()) {
			return null;
		} else {
			for (WorkDepartmentRulePO i: rules) {
				if (Long.parseLong(rule.get(0).get("departmentId").toString())  == i.getDepartmentId()) {
					DepartmentPO po = new DepartmentPO();
					po.setDepartmentId(i.getDepartmentId());
					po.setDepartmentName(rule.get(0).get("departmentName").toString());
					return po;
				}
			}
			DepartmentPO department = new DepartmentPO();
				department.setCompanyId(((JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser()).getCompanyId());
			List<DepartmentPO> departments = departmentServcie.selectList(new EntityWrapper<>(department));
			DepartmentPO  dp = null;
			do {
				dp = findPartment((long) rule.get(0).get("pid"), departments);
				if (dp == null)
					return null;
			} while (Long.parseLong(rule.get(0).get("departmentId").toString()) ==  dp.getDepartmentId());
			
			return dp; 
		}  
	}
	private DepartmentPO findPartment(Long pid, List<DepartmentPO> departments) { 
		for (DepartmentPO po: departments) {
			if (po.getDepartmentId().equals(pid))
				return po; 
		} 
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="今日记录")
	@RequestMapping(value="/todayRecords", method = RequestMethod.POST)
    public Object todayRecords(
    		@RequestBody WorkRecordPO po) throws Exception { 

		WorkRecordPO workRecord = new WorkRecordPO();
			workRecord.setJasoUserId(((JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser()).getJasoUserId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
		
		MulSelect mulSelect = MulSelect.newInstance("${1}", workRecord, new WorkPositionPO()); 
			mulSelect.where("${0}").like("workRecordTime", format.format(new Date()));
			mulSelect.setOrderBy("workrecord.work_record_time");
		List list = (List)service.mulSelect(mulSelect);
		JasoUserPO jasoUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser(); 
		
		Map<String, Object> result = new HashMap<>();
			result.put("userRealName", jasoUser.getUserRealName());
			result.put("userIcon", jasoUser.getUserIcon());
		 
		DepartmentPO dp = getDepartment(); 
		if (dp != null) {
			result.put("departmentName", dp.getDepartmentName());
		}    
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		if (list.size() == 2) {
			result.put("morning", list.get(0));
			result.put("afternoon", list.get(1));
		} else if (list.size() == 1) {
			Date date = (Date)((Map)list.get(0)).get("workRecordTime");
			
			if (sdf.format(date).compareTo("12:00") < 0) {
				result.put("morning", list.get(0));
			} else {
				result.put("morning", null);
				result.put("afternoon", list.get(0));
			} 
		} else {
			Date date = new Date();
			if (sdf.format(date).compareTo("12:00") > 0) {
				result.put("morning", null);
			}
		}
		return result;
    } 
	private List<Integer> getRestDays(String restDays) {
		if (restDays == null) restDays = "";
		
		String[] _restDays = restDays.split(",");
		List<Integer> list = new ArrayList<>();
		
		for (String i: _restDays) {
			list.add(Integer.parseInt(i));
		}
		return list;
	} 
	private void addQueryParams(Calendar start, Calendar end, RecordsParam params, Map<String, Object> config) { 
		int startDay = (int) config.get("calcStartDay"), endDay = (int) config.get("calcEndDay");
		if (startDay >= endDay) {
			start.set(Calendar.YEAR, params.getYear());
			start.set(Calendar.MONTH, params.getMonth() - 1);
			start.set(Calendar.DATE, startDay);
			start.set(Calendar.HOUR, 0);
			
			end.set(Calendar.YEAR, params.getYear());
			end.set(Calendar.MONTH, params.getMonth());
			end.set(Calendar.DATE, params.getDay() + 1);
			end.set(Calendar.HOUR, 0);
		} else {
			start.set(Calendar.YEAR, params.getYear());
			start.set(Calendar.MONTH, params.getMonth());
			start.set(Calendar.DATE, startDay);
			start.set(Calendar.HOUR, 0);
			
			end.set(Calendar.YEAR, params.getYear());
			end.set(Calendar.MONTH, params.getMonth());
			end.set(Calendar.DATE, params.getDay() + 1);
			end.set(Calendar.HOUR, 0);
		} 
	} 
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="",notes="记录")
	@RequestMapping(value="/records", method = RequestMethod.POST)
    public Object records(@RequestBody RecordsParam params) throws Exception {  
		JasoUserPO jasoUser = new JasoUserPO();
			jasoUser.setJasoUserId(((JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser()).getJasoUserId());
		DepartmentPO dpo = getDepartment();
		MulSelect mulSelect = MulSelect.newInstance("${1}",  dpo, new WorkDepartmentRulePO()); 
		
		List<Map<String, Object>> result = (List<Map<String, Object>>) service.mulSelect(mulSelect);
		
		if (result.size() == 1) { 
			WorkRecordPO workRecord = new WorkRecordPO();
				workRecord.setJasoUserId(jasoUser.getJasoUserId());
			Wrapper<WorkRecordPO> ew = new EntityWrapper<>(workRecord); 
			Date date = new Date();
			Calendar start = Calendar.getInstance(), end = Calendar.getInstance(); 
			if (params.getYear() == null) {
				params.setYear(start.get(Calendar.YEAR));
			}
			if (params.getDay() == null) {
				params.setDay(date.getDate());
			}
			if (params.getMonth() == null) {
				params.setMonth(date.getMonth());
			}
			addQueryParams(start, end, params, result.get(0));
			ew.ge("work_record_time", start.getTime()).lt("work_record_time", end.getTime()).orderBy("work_record_time"); 
			List<WorkRecordPO> results = workRecordService.selectList(ew);  

			Map<String, Object> back = new HashMap<>();
			int laterDay = 0, leaveEarlyDay = 0, mustArriveDay = 0, lostDay = 0, workDay = 0, absenteeism = 0, restDay = 0;
			List<WorkRecordPO> laterList = new ArrayList<>(), leaveEarlyList = new ArrayList<>(), mustArriveList = new ArrayList<>(), lostList = new ArrayList<>(), workList = new ArrayList<>(), absenteeismList = new ArrayList<>(),  restList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Set<String> days = new HashSet<>();
			
			for (WorkRecordPO el: results) {
				days.add(sdf.format(el.getWorkRecordTime()));
				if (el.getWorkRecordType() == 1 && el.getWorkLate() == 1) {
					laterDay ++;
					laterList.add(el);
				} else if (el.getWorkRecordType() == 2 && el.getWorkLeaveEarly() == 1) {
					leaveEarlyDay ++;
					leaveEarlyList.add(el);
				}
			}
			
			for (int i = 0; i < results.size(); i ++) {
				WorkRecordPO el = results.get(i);
				if (i + 1 < results.size()) {
					if (sdf.format(el.getWorkRecordTime()).equals(sdf.format(results.get(i + 1).getWorkRecordTime()))) {
						workDay ++;
						workList.add(el);
					} else {
						if (!sdf.format(el.getWorkRecordTime()).equals(sdf.format(date))) {
							lostDay ++;
							lostList.add(el);
						}
					}
				}  
			} 
			List<Integer> _restDays = getRestDays(result.get(0).get("workRestDays").toString());
			 
			if (!days.isEmpty())
				while (!((start.get(Calendar.DATE) == end.get(Calendar.DATE)) && (start.get(Calendar.MONTH) == end.get(Calendar.MONTH)))) {  
					WorkRecordPO wr = new WorkRecordPO();
					wr.setWorkRecordTime(start.getTime());
					if (_restDays.contains(start.get(Calendar.DAY_OF_WEEK) - 1)) {
						restDay ++;
						restList.add(wr);
					} else { 
						if (!days.contains(sdf.format(start.getTime()))) {
							absenteeism ++;
							absenteeismList.add(wr);
						}
						mustArriveDay ++;
						mustArriveList.add(wr); 
					}
					start.add(Calendar.DATE, 1);
				}
			
			back.put("laterDay", laterDay);
			back.put("leaveEarlyDay", leaveEarlyDay);
			back.put("mustArriveDay", mustArriveDay);
			back.put("lostDay", lostDay);
			back.put("workDay", workDay);
			back.put("restDay", restDay);
			//曠工
			back.put("absenteeism", absenteeism);
			
			if (params.getMonth() != null) {
				back.put("laterList", laterList);
				back.put("leaveEarlyList", leaveEarlyList);
				back.put("mustArriveList", mustArriveList);
				back.put("lostList", lostList);
				back.put("workList", workList);
				back.put("absenteeismList", absenteeismList);
				back.put("restList", restList);
			} 
			return back;
		} else {
			return new HashMap<>();
		}  
    }
}
