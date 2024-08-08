package com.sparta.msa_exam.order.global.exception;

public record InvalidInputRes(
        String field,
        String message
) {
}
