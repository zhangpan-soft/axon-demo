package com.dreamvalley.demo.axon.core.base.repository;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangpan
 * @description
 * @date 2021/12/9/009 15:44
 */
public class CustomSimpleRepository<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T,ID> {

    private EntityManager em;
    private JpaEntityInformation<T,?> entityInformation;

    public CustomSimpleRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
        this.entityInformation = entityInformation;
    }

    @Override
    public T insert(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T insertAndFlush(T entity) {
        insert(entity);
        flush();
        return entity;
    }

    @Override
    public T update(T entity) {
        Session session = em.unwrap(Session.class);
        session.update(entity);
        return entity;
    }

    @Override
    public T updateAndFlush(T entity) {
        T update = update(entity);
        flush();
        return update;
    }

    @Override
    public List<T> insertAll(List<T> entities) {
        List<T> list = new ArrayList<>();
        for (T entity : entities) {
            list.add(insert(entity));
        }
        return list;
    }

    @Override
    public List<T> updateAll(List<T> entities) {
        List<T> list = new ArrayList<>();
        for (T entity : entities) {
            list.add(update(entity));
        }
        return list;
    }

}
