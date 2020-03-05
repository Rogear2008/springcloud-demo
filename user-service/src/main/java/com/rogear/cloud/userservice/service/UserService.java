package com.rogear.cloud.userservice.service;

import com.rogear.cloud.userservice.pojo.User;

import java.util.List;

/**
 * Created by Rogear on 2020/3/5
 **/
public interface UserService {

    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    List<User> getByUserName(String username);

    List<User> getUserByIds(List<Long> ids);
}
