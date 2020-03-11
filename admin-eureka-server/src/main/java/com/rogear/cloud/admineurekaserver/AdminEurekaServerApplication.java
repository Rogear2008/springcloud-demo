package com.rogear.cloud.admineurekaserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdminEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminEurekaServerApplication.class, args);
    }

}
