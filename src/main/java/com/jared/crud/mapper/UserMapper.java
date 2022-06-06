package com.jared.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jared.crud.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
