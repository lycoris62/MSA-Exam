package com.sparta.msa_exam.product.domain.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

/**
 * DTO 규칙으로 요청이면 -Req 접미사를 사용하며, 레코드를 사용하여 불변을 보장합니다.
 */
@Schema(title = "상품 추가 요청 DTO")
public record AddProductReq(

        @NotBlank(message = "상품 이름은 공백이 될 수 없습니다.")
        @Size(min = 1, max = 255, message = "상품의 이름은 1글자 이상, 255글자 이하 입니다.")
        @Schema(description = "상품 이름", example = "apple")
        String name,

        @NotNull(message = "공급가를 입력해주세요.")
        @PositiveOrZero(message = "공급가는 음수가 될 수 없습니다.")
        @Max(value = Integer.MAX_VALUE, message = "최대 공급가를 초과하였습니다.")
        @Schema(description = "공급가", example = "1000")
        Integer supply_price
) {
}
