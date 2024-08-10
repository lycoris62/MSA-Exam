package com.sparta.msa_exam.auth.domain.auth.entity;

import com.sparta.msa_exam.auth.domain.auth.dto.request.SignUpReq;
import com.sparta.msa_exam.auth.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users") // 모든 테이블명은 엔티티명에 -s 붙이기
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA Entity 기본 생성자가 필요하므로, 최소 접근제어자로 제한
public class User extends BaseEntity {

    @Id
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public static User from(SignUpReq request) {

        User user = new User();

        user.userId = request.userId();
        user.username = request.username();
        user.password = request.password();
        user.role = "ROLE_USER";

        return user;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
