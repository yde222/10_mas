package com.ohgiraffers.orderservice.command.service;

import com.ohgiraffers.orderservice.command.client.UserClient;
import com.ohgiraffers.orderservice.command.dto.OrderDTO;
import com.ohgiraffers.orderservice.command.entity.Order;
import com.ohgiraffers.orderservice.command.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ModelMapper modelMapper;

    public OrderDTO createOrder(OrderDTO orderDTO, Long userId) {
        // userId를 직접 사용해 주문 생성 전에 사용자의 등급을 알아올 수 있음
        String userStatus = userClient.getUserGrade(userId).getData();
        if (userStatus == null || userStatus.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 사용자 등급입니다.");
        }

        // 주문 생성 로직
        if("PREMIUM".equals(userStatus)) orderDTO.setPrice(orderDTO.getPrice() * 0.9);
        Order order = modelMapper.map(orderDTO, Order.class);
        order.createOrder(userId, LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        orderDTO.setId(savedOrder.getId());
        orderDTO.setOrderDate(savedOrder.getOrderDate());
        return orderDTO;
    }
}
