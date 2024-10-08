package com.sparta.msa_exam.order.domain.order.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateOrderReq(

        @NotBlank(message = "주문 이름은 공백이 될 수 없습니다.")
        @Size(min = 1, max = 255, message = "주문의 이름은 1글자 이상, 255글자 이하 입니다.")
        String name
) {
}
