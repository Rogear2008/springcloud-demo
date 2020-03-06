package com.rogear.cloud.userservice.controller;

import com.rogear.cloud.userservice.pojo.CommonResult;
import com.rogear.cloud.userservice.pojo.User;
import com.rogear.cloud.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rogear on 2020/3/5
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user){
        userService.create(user);
        return new CommonResult("新增成功",200);
    }

    @GetMapping("/{id}")
    public CommonResult<User> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        LOGGER.info("根据id查询用户信息，用户名称：{}",user.getUsername());
        return new CommonResult<User>(user);
    }

    @GetMapping("/getUserByIds")
    public CommonResult<List<User>> getUserByIds(@RequestParam List<Long> ids){
        List<User> userList = userService.getUserByIds(ids);
        LOGGER.info("根据ids获取用户信息，用户列表{}",userList);
        return new CommonResult<>(userList);
    }

    @GetMapping("/getByUsername")
    public CommonResult<List<User>> getByUsername(@RequestParam String username){
        List<User> userList = userService.getByUsername(username);
        LOGGER.info("根据用户名查询用户信息，用户列表{}",userList);
        return new CommonResult<>(userList);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user){
        userService.update(user);
        return new CommonResult(null);
    }

    @PostMapping("/delete")
    public CommonResult delete(@RequestParam Long id){
        userService.delete(id);
        return new CommonResult(null);
    }
}
