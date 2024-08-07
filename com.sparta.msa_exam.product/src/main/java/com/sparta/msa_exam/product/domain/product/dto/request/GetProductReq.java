package com.sparta.msa_exam.product.domain.product.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO 규칙으로 요청이면 -Req 접미사를 사용하며, 레코드를 사용하여 불변을 보장합니다.
 * API 당 필수적으로 독립적인 DTO 포함하도록 하였습니다.
 */
@JsonIgnoreProperties // 필드가 없을 시 직렬화 에러가 나기 때문에 추가 필요
@Schema(title = "상품 목록 조회 DTO")
public record GetProductReq() {
}
