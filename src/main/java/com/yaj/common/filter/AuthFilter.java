package com.yaj.common.filter;

import io.jsonwebtoken.JwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yaj.common.cache.CacheManager;
import com.yaj.common.cache.CacheConfig.CacheTypeEnum;
import com.yaj.common.config.properties.BaseProperties;
import com.yaj.common.constant.UserTypeEnum;
import com.yaj.common.exception.BusinessException;
import com.yaj.common.exception.BusinessExceptionErrorEnum;
import com.yaj.common.inspect.url.URLInspect;
import com.yaj.common.inspect.url.URLInspectTypeEnum;
import com.yaj.common.jwt.JwtTokenUtil;
import com.yaj.common.jwt.JwtUserInfo;
import com.yaj.common.jwt.properties.JwtProperties;
import com.yaj.common.threadlocal.ThreadlocalContext;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.log.tools.LogHelper;
import com.yaj.core.util.JSONUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 对客户端请求的jwt token验证过滤器
 *
 * @author ...
 * @Date 2017/8/24 14:04
 */
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private BaseProperties baseProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String urlPath = request.getServletPath();

        //swager过滤&静态文件过滤
        if(URLInspect.check(urlPath, URLInspectTypeEnum.SWAGGER, URLInspectTypeEnum.STATIC_FILE, URLInspectTypeEnum.DAFAULT_NO_AUTH)){
            chain.doFilter(request, response);
            return;
        }
        //非授权请求过滤
        PathMatcher matcher = new AntPathMatcher();
        List<String> noAuthPathList = baseProperties.getNoAuthPath();
        for(int i=0;i<noAuthPathList.size();i++){
            if(matcher.match(noAuthPathList.get(i), urlPath)){
                chain.doFilter(request, response);
                return;
            }
        }
        //需授权请求

        String requestHeader = request.getHeader(jwtProperties.getHeader());
        if(requestHeader == null) {
        	requestHeader = request.getParameter(jwtProperties.getHeader());
        }
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);

            //验证token是否过期
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    throw new BusinessException(BusinessExceptionErrorEnum.TOKEN_EXPIRED);
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                throw new BusinessException(BusinessExceptionErrorEnum.TOKEN_PARSE_ERROR, e);
            }
        } else {
        	
            //header没有带Bearer字段
//        	chain.doFilter(request, response);
//        	return ;
            throw new BusinessException(BusinessExceptionErrorEnum.TOKEN_SECRET_ERROR);
        }
        //从缓存中获取threadloacalcontext
        JwtUserInfo jwtUserInfo = null;
        try {
        	jwtUserInfo = JSONUtil.parseObject(jwtTokenUtil.getClaimFromToken(authToken).getSubject(), JwtUserInfo.class);
        }catch(Exception e) {
        	throw new BusinessException(BusinessExceptionErrorEnum.TOKEN_PARSE_ERROR,e);
        }
        LogHelper.log(AuthFilter.class,"校验缓存中token...");
        ThreadlocalContext threadlocalContext = null;
        if(jwtUserInfo.getType().equals(UserTypeEnum.WEB)) {
        	threadlocalContext = CacheManager.get(CacheTypeEnum.webTokenCache, jwtUserInfo.getToken(), ThreadlocalContext.class);
        }else  if(jwtUserInfo.getType().equals(UserTypeEnum.APP)) {
        	threadlocalContext = CacheManager.get(CacheTypeEnum.appTokenCache, jwtUserInfo.getToken(), ThreadlocalContext.class);
        }
        //校验缓存中token
        if(threadlocalContext==null) {
        	throw new BusinessException(BusinessExceptionErrorEnum.TOKEN_EXPIRED);
        }else {
        	LogHelper.log(AuthFilter.class,"获取缓存中的值："+JSONUtil.toJSONString(threadlocalContext));
        	ThreadlocalManager.setThreadContext(threadlocalContext);
        	//刷新缓存时间
        	if(jwtUserInfo.getType().equals(UserTypeEnum.WEB)) {
            	CacheManager.put(CacheTypeEnum.webTokenCache, jwtUserInfo.getToken(), threadlocalContext);
            }else  if(jwtUserInfo.getType().equals(UserTypeEnum.APP)) {
            	CacheManager.put(CacheTypeEnum.appTokenCache, jwtUserInfo.getToken(), threadlocalContext);
            }
        }
        chain.doFilter(request, response);
    }
}