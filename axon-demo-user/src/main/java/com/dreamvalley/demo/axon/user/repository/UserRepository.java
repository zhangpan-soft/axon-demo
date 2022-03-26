package com.dreamvalley.demo.axon.user.repository;

import com.dreamvalley.demo.axon.core.base.repository.BaseRepository;
import com.dreamvalley.demo.axon.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户持久化层
 *
 * @author zhangpan
 */
@Repository
public interface UserRepository extends BaseRepository<User,Long> {
}
