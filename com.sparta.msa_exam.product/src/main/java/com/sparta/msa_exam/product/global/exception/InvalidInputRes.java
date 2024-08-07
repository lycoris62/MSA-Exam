package com.sparta.msa_exam.product.global.exception;

public record InvalidInputRes(
        String field,
        String message
) {
}
