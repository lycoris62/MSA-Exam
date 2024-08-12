package com.sparta.msa_exam.order.global.openfeign.client.dto.response;

public record GetProductsRes(
        Long productId,
        String name,
        Integer supply_price
) {
}
