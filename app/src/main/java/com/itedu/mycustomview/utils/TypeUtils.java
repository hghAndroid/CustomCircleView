package com.itedu.mycustomview.utils;

/*
 *
 * 项目名:MyCustomView
 * 包名:com.itedu.mycustomview.utils
 * 创建时间:2018/12/1115:14
 * 描述: 类型安全转换工具
 *
 */public class TypeUtils {
    /**
     * Int 类型安全转换
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static final int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }
}
