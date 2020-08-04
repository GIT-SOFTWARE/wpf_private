package com.biostime.bp.authorization.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/12/5 16:31
 */
public class HtmlStringUtil {

    /**
     * 定义HTML开始标签的正则表达式
     */
    private static final Pattern REGEX_HTML_START_TAG = Pattern.compile("<[.[^<]]*>", Pattern.CASE_INSENSITIVE);

    /**
     * 定义空格回车换行符
     */
    private static final Pattern REGEX_SPACE = Pattern.compile("[\t\r\n]", Pattern.CASE_INSENSITIVE);

    public static String removeHtmlTag(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return "";
        }
        // 过滤html标签
        Matcher htmlStartTagMatcher = REGEX_HTML_START_TAG.matcher(htmlStr);
        String newHtmlStr = htmlStartTagMatcher.replaceAll("");
        // 过滤空格回车标签
        Matcher spaceMatcher = REGEX_SPACE.matcher(newHtmlStr);
        newHtmlStr = spaceMatcher.replaceAll("");

        return StringEscapeUtils.unescapeHtml4(newHtmlStr.trim());
    }

}
