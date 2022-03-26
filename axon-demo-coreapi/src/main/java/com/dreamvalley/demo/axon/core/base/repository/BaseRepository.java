package com.dreamvalley.demo.axon.core.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * jpa基础orm层
 *
 * @author zhangpan
 */
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    @Override
    @Deprecated
    <S extends T> S save(S entity);

    @Deprecated
    @Override
    <S extends T> List<S> saveAll(Iterable<S> entities);

    @Deprecated
    @Override
    <S extends T> S saveAndFlush(S entity);

    /**
     * 新增
     *
     * @param entity 实体
     */
    @Transactional(rollbackFor = Exception.class)
    T insert(T entity);

    /**
     * 新增和刷新
     *
     * @param entity
     */
    @Transactional(rollbackFor = Exception.class)
    T insertAndFlush(T entity);

    /**
     * 修改
     *
     * @param entity 实体
     */
    @Transactional(rollbackFor = Exception.class)
    T update(T entity);

    /**
     * 修改和刷新
     *
     * @param entity
     */
    @Transactional(rollbackFor = Exception.class)
    T updateAndFlush(T entity);

    /**
     * 批量新增
     *
     * @param entities
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    List<T> insertAll(List<T> entities);

    /**
     * 批量修改
     *
     * @param entities
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    List<T> updateAll(List<T> entities);
}
