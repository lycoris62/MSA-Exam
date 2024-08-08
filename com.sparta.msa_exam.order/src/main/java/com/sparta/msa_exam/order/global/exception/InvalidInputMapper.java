package com.sparta.msa_exam.order.global.exception;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.FieldError;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface InvalidInputMapper {

    @Mapping(source = "defaultMessage", target = "message")
    InvalidInputRes toInvalidInputRes(FieldError fieldError);
}
