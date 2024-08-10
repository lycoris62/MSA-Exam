package com.sparta.msa_exam.auth.domain.auth.mapper;

import com.sparta.msa_exam.auth.domain.auth.dto.response.SignUpRes;
import com.sparta.msa_exam.auth.domain.auth.entity.User;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AuthMapper {

    SignUpRes toSignUpRes(User user);
}
