package com.rogear.cloud.hystrixservice.service;

import com.rogear.cloud.hystrixservice.pojo.CommonResult;
import com.rogear.cloud.hystrixservice.pojo.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.concurrent.Future;

/**
 * Created by Rogear on 2020/3/6
 **/
public interface UserService {

    CommonResult getUser(Long id);

    CommonResult getUserException(Long id);

    CommonResult getUserCommand(Long id);

    CommonResult getUserCache(Long id);

    CommonResult removeCache(Long id);

    Future<User> getUserFuture(Long id);
}
