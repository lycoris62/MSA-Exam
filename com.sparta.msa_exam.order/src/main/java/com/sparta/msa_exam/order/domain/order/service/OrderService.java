package com.sparta.msa_exam.order.domain.order.service;

import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderReq;
import com.sparta.msa_exam.order.domain.order.dto.response.CreateOrderRes;
import com.sparta.msa_exam.order.domain.order.entity.Order;
import com.sparta.msa_exam.order.domain.order.mapper.OrderMapper;
import com.sparta.msa_exam.order.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public CreateOrderRes createOrder(CreateOrderReq request) {

        Order order = Order.from(request);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toCreateOrderRes(savedOrder);
    }
}
