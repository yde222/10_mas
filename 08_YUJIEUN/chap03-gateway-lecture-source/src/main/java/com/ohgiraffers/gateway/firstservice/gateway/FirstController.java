package com.ohgiraffers.gateway.firstservice.gateway;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

private Environment environment;

public FirstController(Environment environment) {
    this.environment = environment;
}

@GetMapping("/health")
public String healthCheck() {
    return "First Service is OK. Port = " + environment.getProperty("local.server.port");
}

@GetMapping("/message")
public String message(@RequestHeader("first-request") String header) {
    return "first-request header : " + header;
}