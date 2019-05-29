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
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
        System.out.println("----------------------------------------");
    }
}
