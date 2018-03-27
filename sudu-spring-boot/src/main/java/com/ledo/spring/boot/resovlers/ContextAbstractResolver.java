package com.ledo.spring.boot.resovlers;

import com.ledo.core.exceptions.CommonExceptionConstants;
import com.ledo.core.exceptions.ServiceException;
import com.ledo.jwt.provider.JwtTokenProvider;
import com.ledo.jwt.provider.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

/**
 * Created by konghang on 2017/8/31.
 */
public abstract class ContextAbstractResolver implements HandlerMethodArgumentResolver{

    protected JwtTokenProvider jwtTokenProvider;

    public ContextAbstractResolver(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    protected Object doResolveContext(MethodParameter methodParameter, String name, String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)){
            throw ServiceException.build(CommonExceptionConstants.unlogin_error);
        }

        Class<?> parameterType = methodParameter.getParameterType();
        if(parameterType.isPrimitive() && !parameterType.equals(String.class)){
            return null;
        }

        //对称解密
        Object bindValue = null;
        if(parameterType.equals(String.class)){
            try {
                Jws<Claims> claimsJws = jwtTokenProvider.parseJwtToken(jwtToken);
                bindValue = claimsJws.getBody().get(name);
            }catch (Exception e){
                throw ServiceException.build(CommonExceptionConstants.token_not_valid);
            }
        }else {
            try {
                bindValue = jwtTokenProvider.parseJwtToken(jwtToken, (Class<Payload>) parameterType);
            }catch (Exception e){
                throw ServiceException.build(CommonExceptionConstants.token_not_valid);
            }
        }
        return bindValue;
    }
}
