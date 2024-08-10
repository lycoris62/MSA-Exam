package com.sparta.msa_exam.auth.domain.auth.dto.request;

public record SignUpReq(
        String userId,
        String username,
        String password
) {
}
