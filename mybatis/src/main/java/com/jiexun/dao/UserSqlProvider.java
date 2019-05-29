package com.jiexun.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String findByUid(@Param("uid") Integer uid) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("user").WHERE("uid>=#{uid}");
        return sql.toString();

//        StringBuilder sb = new StringBuilder();
//        sb.append("select * from user where ");
//        sb.append("uid>=#{uid}");
//        return sb.toString();
    }

    public String modifyUserById(@Param("age") Integer age, @Param("email") String email, @Param("uid") Integer uid) {
        StringBuilder sb = new StringBuilder();
        sb.append("update user set ");
        if (age != null){
            sb.append("age=#{age}");
            sb.append(",");
        }
        if (!email.equals("")) {
            sb.append("email=#{email}");
            sb.append(",");
        }
        int lastIndexOf = sb.lastIndexOf(",");
        sb.deleteCharAt(lastIndexOf);
        sb.append(" where uid=#{uid}");
        return sb.toString();
    }
}
