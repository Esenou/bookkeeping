package com.bookkeeping.kg.service;

import com.bookkeeping.kg.entity.User;
import com.bookkeeping.kg.service.base.BaseService;

public interface UserService extends BaseService<User> {
    User findByUsername(String username);
}
