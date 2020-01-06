package com.yaj.xyx.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yaj.jaso.business.measuresitepoint.entity.po.MeasureSitePointPO;

public class ListObjectGroupBy {
	public Integer getCountGroupBy(List<MeasureSitePointPO> test){
		Map<Long, List<MeasureSitePointPO>> collect = test.stream().collect(Collectors.groupingBy(MeasureSitePointPO::getMeasurePointId));
		return collect.size();
	}
	
}
