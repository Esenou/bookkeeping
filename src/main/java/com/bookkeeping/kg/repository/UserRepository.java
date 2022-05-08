package com.bookkeeping.kg.repository;

import com.bookkeeping.kg.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {
    User findByUsername(String userName);
}
