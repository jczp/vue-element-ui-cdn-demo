package com.jared.simplecrud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jared.simplecrud.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
