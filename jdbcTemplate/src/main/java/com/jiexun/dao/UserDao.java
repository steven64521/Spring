package com.jiexun.dao;

import com.jiexun.entity.User;
import com.jiexun.entity.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    /**
     * 新增用户
     * @param user_name
     * @param age
     * @param email
     */
    public void addUser(String user_name, Integer age, String email) {
        String sql = "insert into user(name,age,email) values(:name,:age,:email)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", user_name, Types.VARCHAR);
        parameterSource.addValue("age", age, Types.INTEGER);
        parameterSource.addValue("email", email, Types.VARCHAR);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);
        System.out.println("主键:" + keyHolder.getKey().intValue());
    }


    /**
     * 查询所有用户
     * @return
     */
    public List<User> getAllUser() {
//        String sql = "select * from user where uid>:uid";
        String sql = "select * from user";
//        List<User> users = namedParameterJdbcTemplate.query(sql, new UserMapper());

//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        List<User> users = namedParameterJdbcTemplate.query(sql, parameterSource, new UserMapper());

//        List<User> users = namedParameterJdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
//            @Override
//            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                List<User> list = new ArrayList<>();
//                while (rs.next()) {
//                    User user = new User();
//                    user.setUid(rs.getInt("uid"));
//                    user.setName(rs.getString("name"));
//                    user.setAge(rs.getInt("age"));
//                    user.setEmail(rs.getString("email"));
//                    list.add(user);
//                }
//                return list;
//            }
//        });

        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("uid", 1);
        List<User> users = namedParameterJdbcTemplate.query(sql, paramMap, new UserMapper());
        return users;
    }


    /**
     * 修改记录
     * @param user
     */
    public void modify(User user) {
        String sql = "update user set age=:age where uid=:uid";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("uid", user.getUid());
        parameterSource.addValue("age", user.getAge());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int i = namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);
        System.out.println("主键：" + keyHolder.getKey().intValue());
        System.out.println(i);
    }

    /**
     * 删除记录
     * @param uid
     */
    public void deleterUser(Integer uid) {
        String sql = "delete from user where uid=:uid";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("uid", uid, Types.INTEGER);
        int update = namedParameterJdbcTemplate.update(sql, parameterSource);
        System.out.println(update);
    }

    /**
     * 利用simpleJdbcInsert简化新增
     * @param user_name
     * @param age
     * @param email
     */
    public void add(String user_name, Integer age, String email) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", user_name, Types.VARCHAR);
        parameterSource.addValue("age", age, Types.INTEGER);
        parameterSource.addValue("email", email, Types.VARCHAR);
        int execute = simpleJdbcInsert.withTableName("user").execute(parameterSource);
        System.out.println(execute);
    }
}
