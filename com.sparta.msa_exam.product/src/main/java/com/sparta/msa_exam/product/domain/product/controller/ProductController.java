package com.sparta.msa_exam.product.domain.product.controller;

import com.sparta.msa_exam.product.domain.product.dto.request.AddProductReq;
import com.sparta.msa_exam.product.domain.product.dto.request.GetProductReq;
import com.sparta.msa_exam.product.domain.product.dto.response.AddProductRes;
import com.sparta.msa_exam.product.domain.product.dto.response.GetProductRes;
import com.sparta.msa_exam.product.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<GetProductRes>> getProductList(GetProductReq request) {

        List<GetProductRes> productList = productService.getProductList(request);

        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public ResponseEntity<AddProductRes> addProduct(@RequestBody AddProductReq request) {

        AddProductRes response = productService.addProduct(request);

        return ResponseEntity.ok(response);
    }
}
