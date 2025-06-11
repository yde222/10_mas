package com.ohgiraffers.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.ohgiraffers.orderservice.command.client")
public class Chap04OrderServiceLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap04OrderServiceLectureSourceApplication.class, args);
	}

}
