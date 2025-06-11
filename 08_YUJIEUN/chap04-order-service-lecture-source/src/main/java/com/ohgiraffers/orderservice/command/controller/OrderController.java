package com.ohgiraffers.orderservice.command.controller;

import com.ohgiraffers.orderservice.common.ApiResponse;
import com.ohgiraffers.orderservice.command.dto.OrderDTO;
import com.ohgiraffers.orderservice.command.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(
            @RequestBody OrderDTO orderDTO,
            @AuthenticationPrincipal String userId
            ) {

        OrderDTO createdOrder = orderService.createOrder(orderDTO, Long.valueOf(userId));

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(createdOrder));
    }
}
