package com.yaj.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.log.tools.LogHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @name: 拦截器
 * @author Peach
 * @date:2017/11/19
 */
public class WebIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println(request.getParameter("companyId"));
        LogHelper.log("=========Request Begin...URL:"+request.getRequestURI()+"=========");
        //处理TOKEN
//        if (UrlFilter.urlHandler(request)) {
//            //不拦截通过
//            return true;
//        } else {
//            //验证token（webToken、appToken）
//            return TokenValidate.ValidationToken(request, response);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadlocalManager.removeThreadContext();
        LogHelper.log("=========Request End.=========\n\n");
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
