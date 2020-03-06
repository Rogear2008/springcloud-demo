package com.rogear.cloud.feignservice.service;

import com.rogear.cloud.feignservice.pojo.CommonResult;
import com.rogear.cloud.feignservice.pojo.User;
import com.rogear.cloud.feignservice.service.impl.UserFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rogear on 2020/3/6
 **/
@FeignClient(value = "user-service",fallback = UserFallbackServiceImpl.class)
public interface UserService {

    @PostMapping("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable Long id);

    @GetMapping("/user/getUserByIds")
    CommonResult<List<User>> getUserByIds(@RequestParam List<Long> ids);

    @GetMapping("/user/getByUsername")
    CommonResult<List<User>> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete")
    CommonResult delete(@RequestParam Long id);
}
