package com.dreamvalley.demo.axon.core.base.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 抽象事件
 *
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEvent<ID> implements BaseEvent<ID> {
    private ID id;
    private LocalDateTime createdTime;
}
