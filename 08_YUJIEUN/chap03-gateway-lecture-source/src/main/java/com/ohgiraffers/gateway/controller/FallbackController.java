package com.ohgiraffers.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/fallback/user")
    public Mono<String> userFallback(){
        return Mono.just("User service is not available");
    }
}
