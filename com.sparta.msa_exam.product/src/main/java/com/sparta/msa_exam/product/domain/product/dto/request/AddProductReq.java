package com.sparta.msa_exam.product.domain.product.dto.request;

/**
 * DTO 규칙으로 요청이면 -Req 접미사를 사용하며, 레코드를 사용하여 불변을 보장합니다.
 */
public record AddProductReq(
        String name,
        Integer supplyPrice
) {
}
