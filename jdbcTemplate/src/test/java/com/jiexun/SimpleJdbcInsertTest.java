package com.jiexun;

import com.jiexun.config.NamedParameterJdbcConfig;
import com.jiexun.dao.DeptDao;
import com.jiexun.entity.Dept;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SimpleJdbcInsertTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NamedParameterJdbcConfig.class);
        DeptDao deptDao = (DeptDao) context.getBean("deptDao");

        //新增
//        deptDao.addDept("research", 50);

        //查询所有
        List<Dept> depts = deptDao.getAllDept();
        depts.forEach(dept -> {
            System.out.println(dept.getDeptName());
        });
    }
}
