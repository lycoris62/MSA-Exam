package com.sparta.msa_exam.order.domain.order.dto.response;

import java.util.List;

public record GetOrderRes(
        Long orderId,
        List<Long> productIds
) {
}
