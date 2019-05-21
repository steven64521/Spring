package com.jiexun.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setStu_name(rs.getString("stu_name"));
        student.setStu_num(rs.getInt("stu_num"));
        student.setClass_num(rs.getString("class_num"));
        return student;
    }
}
