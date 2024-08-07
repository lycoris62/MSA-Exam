package com.sparta.msa_exam.product.domain.product.dto.response;

/**
 * DTO 규칙으로 응답이면 -Res 접미사를 사용하며, 레코드를 사용하여 불변을 보장합니다.
 */
public record AddProductRes(
        Long productId,
        String name,
        Integer supplyPrice
) {
}
