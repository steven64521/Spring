package com.jiexun.mapper;

import com.jiexun.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用mybatis原生的功能
 */
public interface StudentMapper {

    @Select(value = "select * from student")
    List<Student> getAllStudents();
}
