package com.jisoo.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jisoo.board.mapper.UserMapper;


@SpringBootTest(classes = TaskApplication.class)
class DbConnectionTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void ping_test() {
        int result = userMapper.ping();
        assertThat(result).isEqualTo(1);
    }
}
