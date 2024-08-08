package com.sparta.msa_exam.order.domain.order.dto.request;

import java.util.List;

public record CreateOrderReq(
        String name,
        List<Long> productIds
) {
}
