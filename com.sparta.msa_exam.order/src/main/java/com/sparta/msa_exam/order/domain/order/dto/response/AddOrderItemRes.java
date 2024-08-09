package com.sparta.msa_exam.order.domain.order.dto.response;

public record AddOrderItemRes(
        Long orderId,
        Long productId
) {
}
