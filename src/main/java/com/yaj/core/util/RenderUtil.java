package com.yaj.core.util;

import com.alibaba.fastjson.JSON;
import com.yaj.core.exception.BaseBusinessException;
import com.yaj.core.exception.BaseExceptionErrorEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 渲染工具类
 *
 * @date 2017-08-25 14:13
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new BaseBusinessException(BaseExceptionErrorEnum.SYSTEM_ERROR);
        }
    }
}
