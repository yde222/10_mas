package com.ohgiraffers.chap05configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Chap05ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap05ConfigServerApplication.class, args);
    }

}
