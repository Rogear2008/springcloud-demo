package com.rogear.cloud.admineurekaclient.service;



import com.rogear.cloud.admineurekaclient.pojo.User;

import java.util.List;

/**
 * Created by Rogear on 2020/3/5
 **/
public interface UserService {

    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    List<User> getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}
