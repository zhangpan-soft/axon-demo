package com.dreamvalley.demo.axon.core.base.entity;

import java.time.LocalDateTime;

/**
 * @author zhangpan
 */
public interface BaseEntity<ID> {
    /**
     * 唯一标识
     * @return 聚合对象唯一标识
     */
    ID getId();

    /**
     * 创建时间
     * @return {@link LocalDateTime}
     */
    LocalDateTime getCreatedTime();

    /**
     * 最后一次更新时间
     * @return {@link LocalDateTime}
     */
    LocalDateTime getUpdatedTime();
}
