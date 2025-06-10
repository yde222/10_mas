package com.ohgiraffers.secondservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondController {
    private Environment environment;

    public SecondController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Second Service is OK. Port = " + environment.getProperty("local.server.port");
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        return "seecond-request header : " + header;
    }

}
