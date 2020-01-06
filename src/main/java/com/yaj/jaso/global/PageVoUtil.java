package com.yaj.jaso.global;

import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
public class PageVoUtil {
	public static Object setPage(Page<?> res){ 
		Map<String, Object> map = new HashMap<>();
		PageVo _page  = new PageVo();
		_page.setPageNo(res.getCurrent());
		_page.setPageSize(res.getSize());
		_page.setTotal(res.getTotal());
		map.put("data", res.getRecords());
		map.put("page", _page);
		return map;
	}
	
}
