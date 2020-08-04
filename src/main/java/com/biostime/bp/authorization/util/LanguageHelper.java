package com.biostime.bp.authorization.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.biostime.bp.authorization.common.GlobalVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 语言类处理工具
 *
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/27 10:06
 */
public class LanguageHelper {

    private static Map<String, Locale> localeMap;

    static {
        localeMap = new HashMap<>(4);
        localeMap.put(GlobalVariable.LANG_ZH_CN, Locale.SIMPLIFIED_CHINESE);
        localeMap.put(GlobalVariable.LANG_EN_US, Locale.US);
        localeMap.put(GlobalVariable.LANG_FR, Locale.FRANCE);
    }

    /**
     * 从当前请求中获取语言地区信息
     *
     * @return 地区语言信息
     */
    public static Locale getLocaleFromRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String langType = null;
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            langType = request.getHeader("lang-type");
            // header中获取不到语言信息，改为从链接地址中获取
            if (StringUtils.isBlank(langType)) {
                langType = request.getParameter("lang-type");
            }
        }

        return StringUtils.isBlank(langType) ? Locale.SIMPLIFIED_CHINESE : localeMap.get(langType) == null ? Locale.SIMPLIFIED_CHINESE : localeMap.get(langType);
    }

    /**
     * 把前端浏览器传过来的语言类型转换为java的语言类型
     *
     * @param browserLangType 前端浏览器的语言类型，如zh-cn
     * @return 语言类型
     */
    public static String transBrowserLang2JavaLang(String browserLangType) {
        if (localeMap.containsKey(browserLangType)) {
            return localeMap.get(browserLangType).toString();
        } else {
            return browserLangType;
        }
    }

}
