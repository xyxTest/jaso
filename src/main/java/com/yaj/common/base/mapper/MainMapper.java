package com.yaj.common.base.mapper;
 
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional; 
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckGet;
 

public interface MainMapper extends SuperMapper{
    public List<Map> mulSelect(MulSelect param);
   // public List<QualityCheckGet> mulSelects(MulSelect param);
    public Integer countMulSelect(MulSelect param);
}
