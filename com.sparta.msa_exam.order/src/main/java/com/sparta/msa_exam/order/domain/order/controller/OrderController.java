package com.sparta.msa_exam.order.domain.order.controller;

import com.sparta.msa_exam.order.domain.order.dto.request.AddOrderItemReq;
import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderReq;
import com.sparta.msa_exam.order.domain.order.dto.response.AddOrderItemRes;
import com.sparta.msa_exam.order.domain.order.dto.response.CreateOrderRes;
import com.sparta.msa_exam.order.domain.order.dto.response.GetOrderRes;
import com.sparta.msa_exam.order.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderRes> getOrder(@PathVariable Long orderId) {

        GetOrderRes response = orderService.getOrder(orderId);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping
    public ResponseEntity<CreateOrderRes> createOrder(@RequestBody @Valid CreateOrderReq request) {

        CreateOrderRes response = orderService.createOrder(request);

        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<AddOrderItemRes> addOrderItem(
            @PathVariable Long orderId,
            @RequestBody @Valid AddOrderItemReq request
    ) {

        AddOrderItemRes response = orderService.addOrderItem(orderId, request);

        return ResponseEntity.ok()
                .body(response);
    }
}
