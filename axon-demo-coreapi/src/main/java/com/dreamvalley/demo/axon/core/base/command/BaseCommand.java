package com.dreamvalley.demo.axon.core.base.command;

import java.time.LocalDateTime;

/**
 * 基础命令对象
 * @author zhangpan
 */
public interface BaseCommand<ID> {

    /**
     * 命令唯一标识
     *
     * @return {@link ID}
     */
    ID getId();


    /**
     * 命令创建时间
     *
     * @return {@link LocalDateTime}
     */
    LocalDateTime getCreatedTime();
}
