package com.zbs.mybatisplus.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyBeanUtil extends BeanUtils {


    /**
     * 拷贝非空数据
     *
     * @param source 源数据
     * @param clazz  需要创建的数据类
     * @param <E>    传入的数据类
     * @return 返回新的类型数据
     */
    public static <E> E copyPropertiesNotNull(Object source, Class<E> clazz) {
        try {
            E e = clazz.newInstance();
            copyPropertiesNotNull(source, e);
            return e;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new FatalBeanException("can not init class" + clazz.getName());
        }
    }


    public static <E> List<E> copyPropertiesListNotNull(List<?> list, Class<E> clazz) {
        if (list == null) {
            return null;
        }
        return list.stream().map(o -> copyPropertiesNotNull(o, clazz)).collect(Collectors.toList());
    }

    public static void copyPropertiesNotNull(Object source, Object target) {
        String[] nullPropertyNames = nullPropertyNames(source);
        BeanUtils.copyProperties(source, target, nullPropertyNames);
    }

    public static String[] nullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
