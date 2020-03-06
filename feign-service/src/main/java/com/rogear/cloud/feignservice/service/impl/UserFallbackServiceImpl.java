package com.rogear.cloud.feignservice.service.impl;

import com.rogear.cloud.feignservice.pojo.CommonResult;
import com.rogear.cloud.feignservice.pojo.User;
import com.rogear.cloud.feignservice.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Rogear on 2020/3/6
 **/
@Component
public class UserFallbackServiceImpl implements UserService {
    @Override
    public CommonResult create(User user) {
        return new CommonResult(getDefaultUser());
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        return new CommonResult(getDefaultUser());
    }

    @Override
    public CommonResult<List<User>> getUserByIds(List<Long> ids) {
        return new CommonResult(getDefaultUser());
    }

    @Override
    public CommonResult<List<User>> getByUsername(String username) {
        return new CommonResult(getDefaultUser());
    }

    @Override
    public CommonResult update(User user) {
        return new CommonResult("调用异常，服务降级",500);
    }

    @Override
    public CommonResult delete(Long id) {
        return new CommonResult("调用异常，服务降级",500);
    }

    public User getDefaultUser(){
        return new User(-1l,"defaultUser","111");
    }
}
