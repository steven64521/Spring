package com.jiexun;

import com.jiexun.config.NamedParameterJdbcConfig;
import com.jiexun.dao.UserDao;
import com.jiexun.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class NamedParameterJdbcTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NamedParameterJdbcConfig.class);
        UserDao userDao = context.getBean(UserDao.class);

        // 新增用户
//        userDao.addUser("Lucy", 20, "Lucy@gmail.com");
        System.out.println("---------------------------------------");
        //查询所有用户
        List<User> users = userDao.getAllUser();
        users.forEach(user -> {
            System.out.println(user.getName());
        });
        System.out.println("---------------------------------------");

        //修改
//        User user = new User();
//        user.setUid(1);
//        user.setAge(30);
//        userDao.modify(user);

        //删除
//        userDao.deleterUser(3);

        //利用SimpleJdbcInsert新增
//        userDao.add("lisi", 30, "steven64521@163.com");

        //测试事务--正常
//        UserService service = context.getBean(UserService.class);
//        service.testTransaction();

        //测试事务---异常
//        UserService service1 = context.getBean(UserService.class);
//        service1.testTransaction1();
    }
}
