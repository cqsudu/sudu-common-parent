package com.ledo.spring.boot.filters;

import com.ledo.core.exceptions.CommonExceptionConstants;
import com.ledo.core.exceptions.ServiceException;
import com.ledo.core.vos.ErrorVO;
import com.ledo.jwt.provider.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.mapper.JsonMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by konghang on 2017/12/25.
 */
public class JwtFilter implements Filter{

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private Set<String> excludes = new HashSet<>();

    private JwtTokenProvider jwtTokenProvider;

    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludes = filterConfig.getInitParameter("excludes");
        if(StringUtils.isNoneBlank(excludes)){
            String[] split = excludes.split(",");
            this.excludes = Arrays.stream(split).collect(Collectors.toSet());
        }
        logger.info("excludes uri path are :" + excludes);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Boolean valid = Boolean.TRUE;
        try {
            doAuthorizationValid(httpServletRequest, httpServletResponse);
        }catch (Exception e){
            valid = Boolean.FALSE;
            sendErrorResponse(httpServletResponse);
        }
        if(valid){
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void doAuthorizationValid(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String path = httpServletRequest.getServletPath();
        if(!excludes.contains(path)){
            String authToken = httpServletRequest.getHeader("Authorization");
            if(StringUtils.isBlank(authToken)){
                throw ServiceException.build(CommonExceptionConstants.unlogin_error);
            }
            Jws<Claims> claimsJws = null;
            //解析，验证
            try {
                claimsJws = jwtTokenProvider.parseJwtToken(authToken);
            }catch (Exception e){
                logger.error("解析验证jwt token出错");
                throw ServiceException.build(CommonExceptionConstants.unlogin_error);
            }

            //过期不再信任
            Date expiration = claimsJws.getBody().getExpiration();
            if(expiration == null || expiration.getTime() < System.currentTimeMillis()){
                logger.error("token已经过期");
                throw ServiceException.build(CommonExceptionConstants.token_expired);
            }

            String moduleContext = claimsJws.getBody().get(JwtTokenProvider.MODULE, String.class);
            httpServletRequest.setAttribute(moduleContext, authToken);
        }
    }

    private void sendErrorResponse(HttpServletResponse httpServletResponse) throws IOException {
        ErrorVO error = new ErrorVO();
        error.setCode(CommonExceptionConstants.unlogin_error.getCode());
        error.setMessage(CommonExceptionConstants.unlogin_error.getMessage());
        sendErrorResponse(httpServletResponse, error);
    }

    private void sendErrorResponse(HttpServletResponse httpServletResponse, ErrorVO error) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(JsonMapper.INSTANCE.toJson(error));
        httpServletResponse.getWriter().flush();
    }

    @Override
    public void destroy() {

    }
}
