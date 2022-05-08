package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.entity.User;
import com.bookkeeping.kg.repository.UserRepository;
import com.bookkeeping.kg.service.UserService;
import com.bookkeeping.kg.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl( UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
