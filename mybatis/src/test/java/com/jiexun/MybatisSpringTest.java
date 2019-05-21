package com.jiexun;

import com.jiexun.config.MybatisConfig;
import com.jiexun.dao.UserDao;
import com.jiexun.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MybatisSpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        //查询所有
        List<User> users = userDao.getAllUsers();
        users.forEach(user -> {
            System.out.println(user.getName());
        });
        System.out.println("-------------------------------------------");

        //查询单条记录
        User user = new User();
        user.setUid(1);
        User userById = userDao.findUserById(user);
        System.out.println(userById.getName());
        System.out.println("------------------------------------------");

        //利用selectProvider实现sql动态拼接
        List<User> userList = userDao.findUsers(1);
        userList.forEach(user1 -> {
            System.out.println(user1.getName());
        });
        System.out.println("-------------------------------------------");

        //利用updateProvider实现sql动态拼接
        userDao.modifyUser(21, "", 10);
    }
}
