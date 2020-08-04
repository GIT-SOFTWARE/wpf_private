package com.biostime.bp.authorization.aop.i18n;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.biostime.bp.authorization.bean.base.BaseResponse;
import com.biostime.bp.authorization.util.LanguageHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * BaseResponse国际化处理切面
 *
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/14 14:26
 */
@Aspect
@Order(6)
@Component
public class BaseResponseI18nAspect {

    @Autowired
    private MessageSource messageSource;

    @Pointcut("execution(public * com.biostime.bp.authorization.controller..*.*Controller.*(..))")
    public void baseResponseI18n() {
    }

    @AfterReturning(returning = "ret", pointcut = "baseResponseI18n()")
    public void doAfterReturning(Object ret) {
        if (ret instanceof BaseResponse) {
            @SuppressWarnings("rawtypes")
			BaseResponse baseResponse = (BaseResponse) ret;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (StringUtils.isNotBlank(baseResponse.getCode()) && attributes != null) {
                // 设置响应seqNo
                HttpServletRequest request = attributes.getRequest();
                if(StringUtils.isBlank(baseResponse.getSeqNo())) {
                	  baseResponse.setSeqNo(String.valueOf(request.getAttribute("seqNo")));
                }
                // 响应信息国际化处理
                baseResponse.setDesc(messageSource.getMessage(baseResponse.getCode(), baseResponse.getArgs(),
                        LanguageHelper.getLocaleFromRequest()));
            }
        }
    }

}
