package com.sparta.msa_exam.order.global.openfeign.client;

import com.sparta.msa_exam.order.global.openfeign.client.dto.response.GetProductsRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<GetProductsRes> getProducts();
}
