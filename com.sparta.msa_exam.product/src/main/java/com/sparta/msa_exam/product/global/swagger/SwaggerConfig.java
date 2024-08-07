package com.sparta.msa_exam.product.global.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(info())
                .components(new Components());
    }

    private Info info() {
        return new Info()
                .title("MSA exam")
                .version("1.0.0");
    }
}
