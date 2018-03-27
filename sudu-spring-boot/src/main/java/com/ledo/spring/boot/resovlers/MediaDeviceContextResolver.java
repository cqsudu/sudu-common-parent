package com.ledo.spring.boot.resovlers;

import com.ledo.core.exceptions.CommonExceptionConstants;
import com.ledo.core.exceptions.ServiceException;
import com.ledo.jwt.provider.JwtTokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

/**
 * Media上下文解析
 *
 * Created by konghang on 2017/8/9.
 */
public class MediaDeviceContextResolver extends ContextAbstractResolver {

    public MediaDeviceContextResolver(JwtTokenProvider jwtTokenProvider) {
        super(jwtTokenProvider);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(MediaDeviceContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        MediaDeviceContext annotation = methodParameter.getParameterAnnotation(MediaDeviceContext.class);
        //获取要绑定的值
        Object jwtTokenObj = nativeWebRequest.getAttribute(annotation.name(), RequestAttributes.SCOPE_REQUEST);
        if(Objects.isNull(jwtTokenObj)){
            throw ServiceException.build(CommonExceptionConstants.unlogin_error);
        }
        String jwtToken = String.valueOf(jwtTokenObj);
        return doResolveContext(methodParameter, annotation.name(), jwtToken);
    }
}
