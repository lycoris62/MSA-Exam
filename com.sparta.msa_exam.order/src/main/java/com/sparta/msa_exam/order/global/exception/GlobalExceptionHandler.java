package com.sparta.msa_exam.order.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final InvalidInputMapper invalidInputMapper;

    /**
     * RequestBody 입력 파라미터 검증 오류 발생에 대한 핸들러
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidationException(MethodArgumentNotValidException e) {

        List<InvalidInputRes> invalidInputResList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(invalidInputMapper::toInvalidInputRes)
                .toList();

        return ResponseEntity.badRequest()
                .body(invalidInputResList);
    }
}
