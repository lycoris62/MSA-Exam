package com.sparta.msa_exam.order.domain.order.service;

import com.sparta.msa_exam.order.domain.order.dto.request.AddOrderItemReq;
import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderReq;
import com.sparta.msa_exam.order.domain.order.dto.response.AddOrderItemRes;
import com.sparta.msa_exam.order.domain.order.dto.response.CreateOrderRes;
import com.sparta.msa_exam.order.domain.order.dto.response.GetOrderRes;
import com.sparta.msa_exam.order.domain.order.entity.Order;
import com.sparta.msa_exam.order.domain.order.mapper.OrderMapper;
import com.sparta.msa_exam.order.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public GetOrderRes getOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return orderMapper.toGetOrderReq(order);
    }

    @Transactional
    public CreateOrderRes createOrder(CreateOrderReq request) {

        Order order = Order.from(request);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toCreateOrderRes(savedOrder);
    }

    @Transactional
    public AddOrderItemRes addOrderItem(Long orderId, AddOrderItemReq request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        order.addOrderItem(request);

        return new AddOrderItemRes(orderId, request.productId());
    }
}
