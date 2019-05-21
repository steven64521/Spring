package com.jiexun.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dept implements RowMapper<Dept> {
    private Integer id;
    private String deptName;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Dept() {
    }

    public Dept(Integer id, String deptName, Integer num) {
        this.id = id;
        this.deptName = deptName;
        this.num = num;
    }

    @Override
    public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dept dept = new Dept();
        dept.setId(rs.getInt("id"));
        dept.setDeptName(rs.getString("deptName"));
        dept.setNum(rs.getInt("num"));
        return dept;
    }
}
