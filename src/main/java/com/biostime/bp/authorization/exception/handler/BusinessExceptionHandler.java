package com.biostime.bp.authorization.exception.handler;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.biostime.bp.authorization.bean.base.BaseResponse;
import com.biostime.bp.authorization.exception.BusinessException;
import com.biostime.bp.authorization.util.LanguageHelper;

/**
 * 参数校验失败处理类
 *
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/26 15:14
 */
@ControllerAdvice
@Slf4j
@SuppressWarnings("rawtypes")
public class BusinessExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return handleBindingResult(exception.getBindingResult());
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity handleBindException(BindException exception) {
        return handleBindingResult(exception.getBindingResult());
    }
    


    private ResponseEntity handleBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final ObjectError objectError = bindingResult.getAllErrors().get(0);
            String errorCode = objectError.getDefaultMessage();
            if (objectError instanceof FieldError) {
                return ResponseEntity.ok(BaseResponse.buildResp(errorCode,
                		messageSource.getMessage(errorCode, new String[]{((FieldError) objectError).getField()}, LanguageHelper.getLocaleFromRequest())));
            } else {
                return ResponseEntity.ok(BaseResponse.buildResp(errorCode,
                		messageSource.getMessage(errorCode, null, LanguageHelper.getLocaleFromRequest())));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException exception,HttpServletRequest request) {
    	String seqNo = String.valueOf(request.getAttribute("seqNo"));
    	exception.getBaseResponse().setSeqNo(seqNo);
        exception.getBaseResponse().setDesc(messageSource.getMessage(
                exception.getBaseResponse().getCode(), exception.getBaseResponse().getArgs(), LanguageHelper.getLocaleFromRequest()));
        return ResponseEntity.ok(exception.getBaseResponse());
    }

   
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception exception,HttpServletRequest request) {
    	String seqNo = String.valueOf(request.getAttribute("seqNo"));
    	String sysError = seqNo==null?"system error:":MessageFormat.format("system error seqNo:{0}:", seqNo);
        log.error(sysError, exception);
        BaseResponse<Object> buildResp = BaseResponse.buildResp("4002",
        		messageSource.getMessage("4002", null, LanguageHelper.getLocaleFromRequest()));
        buildResp.setSeqNo(seqNo);
        return ResponseEntity.ok(buildResp);
    }

}
