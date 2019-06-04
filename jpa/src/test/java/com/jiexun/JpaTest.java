package com.jiexun;

import com.jiexun.config.DataSourceConfig;
import com.jiexun.config.SpringJPAConfig;
import com.jiexun.dao.UserRepository;
import com.jiexun.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JpaTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJPAConfig.class, DataSourceConfig.class);

        UserRepository userRepository = context.getBean(UserRepository.class);
        // 查询所有
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
        System.out.println("----------------------------------------");

        //根据id查询
        User user = userRepository.findUserByUid(6);
        System.out.println(user);
        System.out.println("-----------------------------------------");

        //新增
        User user2 = new User();
        user2.setAge(31);
        user2.setName("sammy");
        user2.setEmail("sammy@gmail.com");
        userRepository.save(user2);
        System.out.println("-------------------------------------------");

        User user3 = new User();
        user3.setUid(11);
        user3.setEmail("sammyLoveLucy@gmail.com");
        int i = userRepository.saveAndFlush(user3);
        System.out.println(i);
    }
}
