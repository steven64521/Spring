package com.jiexun.dao;

import com.jiexun.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门操作
 */
@Repository
public class DeptDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    SimpleJdbcInsert simpleJdbcInsert;

    /**
     * 新增部门
     * @param deptName
     * @param num
     */
    public void addDept(String deptName, Integer num) {
//        int i = jdbcTemplate.update("insert into dept(deptName,num) values(?, ?)", deptName, num);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("deptName", deptName);
        mapSqlParameterSource.addValue("num", num);
        int i = simpleJdbcInsert.withTableName("dept").execute(mapSqlParameterSource);
        System.out.println("新增成功:" + i + "条记录");
    }

    /**
     * 获取所有数据
     * @return
     */
    public List<Dept> getAllDept() {
        List<Dept> depts = jdbcTemplate.query("select * from dept", new Dept());
        return depts;
    }
}
