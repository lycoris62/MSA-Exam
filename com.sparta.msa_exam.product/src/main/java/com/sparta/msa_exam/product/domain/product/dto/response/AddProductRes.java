package com.sparta.msa_exam.product.domain.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO 규칙으로 응답이면 -Res 접미사를 사용하며, 레코드를 사용하여 불변을 보장합니다.
 */
@Schema(title = "상품 추가 응답 DTO")
public record AddProductRes(

        @Schema(description = "상품 아이디", example = "1")
        Long productId,

        @Schema(description = "상품 이름", example = "apple")
        String name,

        @Schema(description = "공급가", example = "1000")
        Integer supplyPrice
) {
}
