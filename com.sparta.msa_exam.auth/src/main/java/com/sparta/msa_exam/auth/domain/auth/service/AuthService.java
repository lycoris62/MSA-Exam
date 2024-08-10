package com.sparta.msa_exam.auth.domain.auth.service;

import com.sparta.msa_exam.auth.domain.auth.dto.request.SignInReq;
import com.sparta.msa_exam.auth.domain.auth.dto.request.SignUpReq;
import com.sparta.msa_exam.auth.domain.auth.dto.response.SignInRes;
import com.sparta.msa_exam.auth.domain.auth.dto.response.SignUpRes;
import com.sparta.msa_exam.auth.domain.auth.entity.User;
import com.sparta.msa_exam.auth.domain.auth.mapper.AuthMapper;
import com.sparta.msa_exam.auth.domain.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
public class AuthService {

    private final AuthMapper authMapper;
    private final SecretKey secretKey;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    public AuthService(
            @Value("${service.jwt.secret-key}") String secretKey,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthMapper authMapper
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper;
    }

    /**
     * 테스트 유저 추가
     */
    @PostConstruct
    void setup() {
        User user = User.from(new SignUpReq("testId", "testUsername", "1234"));
        user.setEncodedPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * 사용자 ID를 받아 JWT 액세스 토큰 생성
     */
    public String createAccessToken(String userId, String role) {
        return Jwts.builder()
                .claim("user_id", userId)
                .claim("role", role)
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, io.jsonwebtoken.SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 회원가입
     */
    @Transactional
    public SignUpRes signUp(SignUpReq request) {

        validateUserId(request);

        User user = User.from(request);
        user.setEncodedPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);

        return authMapper.toSignUpRes(savedUser);
    }

    private void validateUserId(SignUpReq request) {
        boolean existsById = userRepository.existsById(request.userId());
        if (existsById) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    /**
     * 사용자 인증
     */
    @Transactional
    public SignInRes signIn(SignInReq request) {

        User user = userRepository.findById(request.user_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        return new SignInRes(createAccessToken(user.getUserId(), user.getRole()));
    }
}
