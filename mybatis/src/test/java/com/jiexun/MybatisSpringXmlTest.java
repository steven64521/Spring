package com.jiexun;

import com.jiexun.entity.User;
import com.jiexun.mapper.UserMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 以xml配置文件方式集成mybatis测试
 */
public class MybatisSpringXmlTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");

        UserMapper userMapper = context.getBean(UserMapper.class);
        System.out.println(userMapper);
        List<User> allUsers = userMapper.getAllUsers();
        allUsers.forEach(user -> {
            System.out.println(user.getName());
        });
        System.out.println("-------------------------------------->");

        User user = new User();
        user.setUid(1);
        User userById = userMapper.findUserById(user);
        System.out.println(userById.getName());
    }
}
