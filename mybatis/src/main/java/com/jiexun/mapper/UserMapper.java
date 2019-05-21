package com.jiexun.mapper;

import com.jiexun.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> getAllUsers();

    User findUserById(User user);
}
