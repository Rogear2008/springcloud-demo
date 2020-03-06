package com.rogear.cloud.hystrixservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.rogear.cloud.hystrixservice.pojo.CommonResult;
import com.rogear.cloud.hystrixservice.pojo.User;
import com.rogear.cloud.hystrixservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by Rogear on 2020/3/6
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public CommonResult getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl+"/user/{1}",CommonResult.class,id);
    }

    public CommonResult getDefaultUser(@PathVariable Long id){
        User defaultUser = new User(-1l,"defaultUser","111");
        return new CommonResult(defaultUser);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser",ignoreExceptions = {NullPointerException.class})
    public CommonResult getUserException(Long id) {
        if (1 == id){
            throw new NullPointerException();
        } else if (2 == id){
            throw new IndexOutOfBoundsException();
        }
        return restTemplate.getForObject(userServiceUrl+"/user/{1}",CommonResult.class,id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser",commandKey = "getUserCommand",groupKey = "getUserGroup",
    threadPoolKey = "getUserThreadPool")
    public CommonResult getUserCommand(Long id) {
        return restTemplate.getForObject(userServiceUrl+"/user/{1}",CommonResult.class,id);
    }

    @Override
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser",commandKey = "getUserCache")
    public CommonResult getUserCache(Long id) {
        LOGGER.info("getUserCache id:{1}",id);
        return restTemplate.getForObject(userServiceUrl+"/user/{1}",CommonResult.class,id);
    }

    public String getCacheKey(Long id){
        return String.valueOf(id);
    }

    @Override
    @CacheRemove(commandKey = "getUserCache",cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public CommonResult removeCache(Long id) {
        LOGGER.info("removeCache id:{1}",id);
        return restTemplate.getForObject(userServiceUrl+"/user/{1}",CommonResult.class,id);
    }

    @Override
    @HystrixCollapser(batchMethod = "getUserByIds",collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds",value = "100")
    })
    public Future<User> getUserFuture(Long id) {
        return new AsyncResult<User>(){
            public User invoke(){
                CommonResult commonResult = restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
                Map data = (Map) commonResult.getData();
                User user = BeanUtil.mapToBean(data, User.class, true);
                LOGGER.info("getUserById username:{1}",user.getUsername());
                return user;
            }
        };
    }

    @HystrixCommand
    public List<User> getUserByIds(List<Long> ids){
        LOGGER.info("getUserByIds ids:{}",ids);
        CommonResult commonResult = restTemplate.getForObject(userServiceUrl + "/user/getUserByIds?ids={1}",
                CommonResult.class, CollUtil.join(ids,","));
        return (List<User>) commonResult.getData();
    }
}
