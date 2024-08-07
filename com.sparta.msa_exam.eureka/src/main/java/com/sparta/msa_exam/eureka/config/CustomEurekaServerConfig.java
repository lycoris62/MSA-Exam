package com.sparta.msa_exam.eureka.config;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

/**
 * 유레카 설정 클래스를 따로 뺀 이유와 명명법은 아래 링크에서 확인하실 수 있습니다.
 *
 * @see <a href="https://promisingmoon.tistory.com/193">EurekaServerConfig 빈 중복 해결하기</a>
 */
@Configuration
@EnableEurekaServer
public class CustomEurekaServerConfig {
}
