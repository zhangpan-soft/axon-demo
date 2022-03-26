package com.dreamvalley.demo.axon.config;

import org.hibernate.dialect.MySQL8Dialect;

/**
 * 设置jpa编码
 * @author zhangpan
 */
public class MySql5InnodbDialectUtf8mb4 extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci";
    }
}
