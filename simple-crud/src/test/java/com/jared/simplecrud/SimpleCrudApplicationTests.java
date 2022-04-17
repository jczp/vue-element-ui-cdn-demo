package com.jared.simplecrud;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jared.simplecrud.entity.User;
import com.jared.simplecrud.mapper.UserMapper;
import com.jared.simplecrud.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootTest
class SimpleCrudApplicationTests {

    @Autowired
    public UserService userService;

    @Test
    public void textselect() {

    }

}
