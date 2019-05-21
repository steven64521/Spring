package com.jiexun.service;

import com.jiexun.dao.UserDao;
import com.jiexun.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * service层
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 正常情况，全部提交
     */
    @Transactional
    public void testTransaction() {
        //新增一个用户
        userDao.add("sam", 18, "sam@gmail.com");
        System.out.println("------------------------------------------");

        //修改一个用
        User user = new User();
        user.setUid(1);
        user.setAge(35);
        userDao.modify(user);
        System.out.println("------------------------------------------");
    }


    /**
     * 手动抛异常，看是否会回滚
     */
    @Transactional
    public void testTransaction1() {
        //新增一个用户
        userDao.add("lily", 18, "lily@gmail.com");
        System.out.println("------------------------------------------");

        //修改一个用
        User user = new User();
        user.setUid(2);
        user.setAge(40);
        userDao.modify(user);
        System.out.println("------------------------------------------");

        //手动抛异常
        System.out.println(3/0);
    }
}
