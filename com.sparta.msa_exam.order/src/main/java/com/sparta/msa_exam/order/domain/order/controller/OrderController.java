package com.sparta.msa_exam.order.domain.order.controller;

import com.sparta.msa_exam.order.domain.order.dto.request.AddOrderItemReq;
import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderReq;
import com.sparta.msa_exam.order.domain.order.dto.response.AddOrderItemRes;
import com.sparta.msa_exam.order.domain.order.dto.response.CreateOrderRes;
import com.sparta.msa_exam.order.domain.order.service.OrderService;
import com.sparta.msa_exam.order.global.common.GlobalConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Value("${server.port}")
    private String port;

    @PostMapping
    public ResponseEntity<CreateOrderRes> createOrder(@RequestBody @Valid CreateOrderReq request) {

        CreateOrderRes response = orderService.createOrder(request);

        return ResponseEntity.ok()
                .header(GlobalConstant.CUSTOM_SERVER_PORT_HEADER, port)
                .body(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<AddOrderItemRes> addOrderItem(@PathVariable Long orderId, @RequestBody @Valid AddOrderItemReq request) {

        AddOrderItemRes response = orderService.addOrderItem(orderId, request);

        return ResponseEntity.ok()
                .header(GlobalConstant.CUSTOM_SERVER_PORT_HEADER, port)
                .body(response);
    }
}
