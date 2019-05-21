package com.jiexun;

import com.jiexun.config.JdbcConfig;
import com.jiexun.dao.StudentDao;
import com.jiexun.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JdbcTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);
//        studentDao.create("zhaoliu", 20190508, "1929");
        System.out.println("-------------------------------------------");
        List<Student> students = studentDao.getAllStudents();
        students.forEach(student -> {
            System.out.println(student.getStu_name());
        });
        System.out.println("---------------------------------------------------");
//        Student student = studentDao.getStudentById(4);
//        System.out.println(student.getStu_name());
//        System.out.println("----------------------------------------------");
        studentDao.updateStu(4, "1330");
        Student student1 = studentDao.getStudentById(4);
        System.out.println(student1.getClass_num());
        System.out.println("---------------------------------------------");
//        studentDao.deleteStu(5);

    }
}
