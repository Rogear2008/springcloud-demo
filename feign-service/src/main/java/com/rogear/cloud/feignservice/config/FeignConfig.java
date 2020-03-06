package com.rogear.cloud.feignservice.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Rogear on 2020/3/6
 **/
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevelConfig(){
        return Logger.Level.FULL;
    }
}
