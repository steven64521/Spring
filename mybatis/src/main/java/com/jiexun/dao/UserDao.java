package com.jiexun.dao;

import com.jiexun.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 操作用户
 */
public interface UserDao {

    @Select(value = "select * from user")
    List<User> getAllUsers();

    @Select(value = "select * from user where uid=#{uid}")
    User findUserById(User user);

    @SelectProvider(method = "findByUid", type = UserSqlProvider.class)
    List<User> findUsers(@Param("uid") Integer uid);

    @UpdateProvider(type = UserSqlProvider.class, method = "modifyUserById")
    void modifyUser(@Param("age") Integer age, @Param("email") String email, @Param("uid") Integer uid);
}
