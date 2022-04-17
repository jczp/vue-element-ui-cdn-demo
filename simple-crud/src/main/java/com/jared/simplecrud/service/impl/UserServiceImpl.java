package com.jared.simplecrud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jared.simplecrud.entity.User;
import com.jared.simplecrud.mapper.UserMapper;
import com.jared.simplecrud.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
