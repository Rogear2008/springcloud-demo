package com.rogear.cloud.feignservice.controller;

import com.rogear.cloud.feignservice.pojo.CommonResult;
import com.rogear.cloud.feignservice.pojo.User;
import com.rogear.cloud.feignservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rogear on 2020/3/6
 **/
@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public CommonResult<User> getUser(@PathVariable Long id){
        return userService.getUser(id);
    };

    @GetMapping("/getUserByIds")
    public CommonResult<List<User>> getUserByIds(@RequestParam List<Long> ids){
        return getUserByIds(ids);
    };

    @GetMapping("/getByUsername")
    public CommonResult<List<User>> getByUsername(@RequestParam String username){
        return userService.getByUsername(username);
    };

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user){
        return userService.update(user);
    };

    @PostMapping("/delete")
    public CommonResult delete(@RequestParam Long id){
        return userService.delete(id);
    };
}
