package com.biostime.bp.authorization.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * 扩展BeanUtils工具类
 *
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/10/10 15:56
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 获取对象中的空属性
     *
     * @param source 源对象
     * @return source中的空属性名称
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 复制对象属性，忽略源对象中的空值
     *
     * @param src    源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

}
