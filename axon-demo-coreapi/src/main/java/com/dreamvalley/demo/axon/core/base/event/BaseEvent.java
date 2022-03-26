package com.dreamvalley.demo.axon.core.base.event;

import java.time.LocalDateTime;

/**
 * 基础事件体
 *
 * @author zhangpan
 */
public interface BaseEvent<ID> {
    /**
     * 唯一标识
     *
     * @return {@link ID}
     */
    ID getId();

    /**
     * 创建时间
     *
     * @return {@link LocalDateTime}
     */
    LocalDateTime getCreatedTime();
}
