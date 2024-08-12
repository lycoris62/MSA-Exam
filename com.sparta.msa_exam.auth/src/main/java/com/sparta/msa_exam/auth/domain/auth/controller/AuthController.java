package com.sparta.msa_exam.auth.domain.auth.controller;

import com.sparta.msa_exam.auth.domain.auth.dto.request.SignInReq;
import com.sparta.msa_exam.auth.domain.auth.dto.request.SignUpReq;
import com.sparta.msa_exam.auth.domain.auth.dto.response.SignInRes;
import com.sparta.msa_exam.auth.domain.auth.dto.response.SignUpRes;
import com.sparta.msa_exam.auth.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public ResponseEntity<SignInRes> createAuthenticationToken(SignInReq request) {

        SignInRes response = authService.signIn(request);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/signUp")
    public ResponseEntity<SignUpRes> signUp(@RequestBody @Valid SignUpReq request) {

        SignUpRes response = authService.signUp(request);

        return ResponseEntity.ok()
                .body(response);
    }
}