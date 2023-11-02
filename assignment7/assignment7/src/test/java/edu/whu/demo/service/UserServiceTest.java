package edu.whu.demo.service;

import edu.whu.demo.domain.User;
import edu.whu.demo.domain.UserDto;
import edu.whu.demo.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void getUser(){UserDto userdto = userService.getUser("user1");
        assertNotNull(userdto);
        assertTrue(new BCryptPasswordEncoder().matches("abc", userdto.getPassword()));
    }
}
