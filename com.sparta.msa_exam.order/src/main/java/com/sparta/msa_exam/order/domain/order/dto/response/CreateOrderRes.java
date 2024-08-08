package com.sparta.msa_exam.order.domain.order.dto.response;

import java.util.List;

public record CreateOrderRes(
        Long orderId,
        String name,
        List<Long> productIds
) {
}
