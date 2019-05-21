package com.jiexun.dao;

import com.jiexun.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param stu_name
     * @param stu_num
     * @param class_num
     */
    public void create(String stu_name, Integer stu_num, String class_num) {
        String sql = "insert into student(stu_name,stu_num,class_num) values(?,?,?)";
//        jdbcTemplate.update(sql, stu_name, stu_num, class_num);

        jdbcTemplate.update(sql, new Object[]{stu_name, stu_num, class_num}, new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
        System.out.println("新增学生" + stu_name);
    }

    /**
     *
     * @return
     */
    public List<Student> getAllStudents() {
        String sql = "select * from student";
//        List<Student> list = jdbcTemplate.query(sql, new StudentMapper());
        List<Student> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Student>>() {
            @Override
            public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Student> students = new ArrayList<>();
                while (rs.next()) {
                    Student stu = new Student();
                    stu.setId(rs.getInt("id"));
                    stu.setStu_name(rs.getString("stu_name"));
                    stu.setStu_num(rs.getInt("stu_num"));
                    stu.setClass_num(rs.getString("class_num"));
                    students.add(stu);
                }
                return students;
            }
        });
        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    public Student getStudentById(Integer id) {
        String sql = "select * from student where id=?";
//        Student student = jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
//        Student student = jdbcTemplate.queryForObject(sql, new StudentMapper(), id);
        Student student = jdbcTemplate.query(sql, new ResultSetExtractor<Student>() {
            @Override
            public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
                Student stu = new Student();
                while (rs.next()) {
                    stu.setId(rs.getInt("id"));
                    stu.setStu_name(rs.getString("stu_name"));
                    stu.setStu_num(rs.getInt("stu_num"));
                    stu.setClass_num(rs.getString("class_num"));
                }
                return stu;
            }
        }, id);

        return student;
    }

    /**
     * 修改数据
     * @param
     * @param
     */
    public void updateStu(Integer id, String class_num) {
        String sql = "update student set class_num=? where id=?";
//        jdbcTemplate.update(sql, class_num, id);

        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setObject(1, class_num, Types.VARCHAR);
                ps.setInt(2, id);
            }
        });
    }


    public void deleteStu(Integer id) {
        String sql = "delete from student where id=?";
//        jdbcTemplate.update(sql, id);

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, id);
                return statement;
            }
        });
    }

}
