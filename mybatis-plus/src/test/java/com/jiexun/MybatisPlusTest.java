package com.jiexun;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiexun.config.MybatisPlusConfig;
import com.jiexun.entity.Student;
import com.jiexun.entity.User;
import com.jiexun.mapper.StudentMapper;
import com.jiexun.mapper.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MybatisPlusTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MybatisPlusConfig.class);

        UserMapper userMapper = context.getBean(UserMapper.class);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(user -> {
            System.out.println(user.getName());
        });
        System.out.println("-------------------------------------->");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name", "zhangsan").eq("uid", 10);
        userMapper.update(new User(), updateWrapper);
        System.out.println("-------------------------->");

        StudentMapper studentMapper = context.getBean(StudentMapper.class);
        List<Student> students = studentMapper.getAllStudents();
        students.forEach(student -> {
            System.out.println(student.getStuName());
        });
    }
}
