package com.biostime.bp.authorization.aop.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.biostime.bp.authorization.common.GlobalVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面处理类
 *
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/11 16:20
 */
@Aspect
@Order(5)
@Component
@Slf4j
public class WebLogAspect {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.biostime.bp.authorization.controller..*.*Controller.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 设置请求的seqNo
            request.setAttribute("seqNo", String.valueOf(System.currentTimeMillis()));
            // 记录下请求内容
            log.info("URL : " + request.getRequestURL().toString());
            log.info("HTTP_METHOD : " + request.getMethod());
            log.info("IP : " + request.getRemoteAddr());
            log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
            log.info("SEQ_NO : " + request.getAttribute("seqNo"));
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String seqNo = null;
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            seqNo = String.valueOf(request.getAttribute("seqNo"));
        }
        // 处理完请求，返回内容
        long diffTime = System.currentTimeMillis() - startTime.get();
        log.info("RESPONSE : " + ret);
        log.info("SPEND TIME : {}ms", diffTime);
        log.info("SEQ_NO : {}", seqNo);
        if (diffTime > GlobalVariable.SERIOUS_PERFORMANCE_PROBLEMS_TIME_THRESHOLD) {
            log.warn("严重注意：该方法可能存在严重性能问题");
        } else if (diffTime > GlobalVariable.GENERAL_PERFORMANCE_PROBLEMS_TIME_THRESHOLD) {
            log.warn("注意：该方法可能存在一般性能问题");
        } else if (diffTime > GlobalVariable.NEED_OPTIMIZE_TIME_THRESHOLD) {
            log.warn("提示：检查该方法是否有优化的空间");
        }
        startTime.remove();
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.trace("Exception in {}.{}() with cause = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getCause() : "NULL");
        startTime.remove();
    }

}
