package com.rogear.cloud.consuluserservice.service.impl;

import com.rogear.cloud.consuluserservice.pojo.User;
import com.rogear.cloud.consuluserservice.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rogear on 2020/3/5
 **/
@Service
public class UserServiceImpl implements UserService {

    private List<User> userList;

    @Override
    public void create(User user) {
        userList.add(user);
    }

    @Override
    public User getUser(Long id) {
        List<User> findUserList = userList.stream().filter(userItem -> userItem.getId().equals(id)).collect(Collectors.toList());
        if (!findUserList.isEmpty()){
            return findUserList.get(0);
        }
        return null;
    }

    @Override
    public void update(User user) {
        userList.stream().filter(userItem -> userItem.getId().equals(user.getId())).forEach(userItem ->{
            userItem.setUsername(user.getUsername());
            userItem.setPasswod(user.getPasswod());
        });
    }

    @Override
    public void delete(Long id) {
        User user = this.getUser(id);
        if (null != user){
            userList.remove(user);
        }
    }

    @Override
    public List<User> getByUsername(String username) {
        List<User> findUserList = userList.stream().filter(userItem -> userItem.getUsername().equals(username)).collect(Collectors.toList());
        return findUserList;
    }

    @Override
    public List<User> getUserByIds(List<Long> ids) {
        List<User> findUserList = userList.stream().filter(userItem -> ids.contains(userItem.getId())).collect(Collectors.toList());
        return findUserList;
    }

    @PostConstruct
    private void initDate(){
        userList = new ArrayList<>();
        userList.add(new User(1L,"张三","111"));
        userList.add(new User(2L,"李四","222"));
        userList.add(new User(3L,"王五","333"));
    }
}
