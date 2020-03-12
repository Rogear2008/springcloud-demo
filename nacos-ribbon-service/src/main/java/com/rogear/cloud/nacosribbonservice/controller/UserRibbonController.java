package com.rogear.cloud.nacosribbonservice.controller;

import com.rogear.cloud.nacosribbonservice.pojo.CommonResult;
import com.rogear.cloud.nacosribbonservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Rogear on 2020/3/5
 **/
@RestController
@RequestMapping("/user")
public class UserRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @GetMapping("/{id}")
    public CommonResult getUser(@PathVariable Long id){
        return restTemplate.getForObject(userServiceUrl+"/user/{1}",CommonResult.class,id);
    }

    @GetMapping("/getByUsername")
    public CommonResult getByUsername(@RequestParam String username){
        return restTemplate.getForObject(userServiceUrl+"/user/getByUsername?username={1}",CommonResult.class,username);
    }

    @GetMapping("/getEntityByUsername")
    public CommonResult getEntityByUsername (@RequestParam String username){
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(userServiceUrl+"/user/getByUsername?username={1}",CommonResult.class,username);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        }
        return new CommonResult("失败",500);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user){
        return restTemplate.postForObject(userServiceUrl+"/user/create",user,CommonResult.class);
    }

    @PostMapping("/delete")
    public CommonResult delete(@PathVariable Long id){
        return restTemplate.postForObject(userServiceUrl+"/user/delete/{1}",null,CommonResult.class);
    }
}
