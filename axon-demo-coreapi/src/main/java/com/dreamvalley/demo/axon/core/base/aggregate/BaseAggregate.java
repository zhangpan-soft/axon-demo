package com.dreamvalley.demo.axon.core.base.aggregate;

import java.time.LocalDateTime;

/**
 * 基础聚合体
 * @author zhangpan
 */
public interface BaseAggregate<ID> {

    /**
     * 获取聚合对象唯一标识
     * @return 聚合对象唯一标识
     */
    ID getId();

    /**
     * 获取聚合对象的创建时间
     * @return {@link LocalDateTime}
     */
    LocalDateTime getCreatedTime();

    /**
     * 聚合对象的最后一次更新时间
     * @return {@link LocalDateTime}
     */
    LocalDateTime getUpdatedTime();
}
