package com.sparta.msa_exam.order.domain.order.dto.request;

import com.sparta.msa_exam.order.domain.order.entity.Order;

public record CreateOrderItemReq(
        Order order,
        Long productId
) {
}
