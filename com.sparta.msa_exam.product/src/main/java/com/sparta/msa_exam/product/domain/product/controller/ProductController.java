package com.sparta.msa_exam.product.domain.product.controller;

import com.sparta.msa_exam.product.domain.product.dto.request.AddProductReq;
import com.sparta.msa_exam.product.domain.product.dto.request.GetProductsReq;
import com.sparta.msa_exam.product.domain.product.dto.response.AddProductRes;
import com.sparta.msa_exam.product.domain.product.dto.response.GetProductsRes;
import com.sparta.msa_exam.product.domain.product.service.ProductService;
import com.sparta.msa_exam.product.global.common.GlobalConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Value("${server.port}")
    private String port;

    /**
     * 상품 목록 조회 API
     */
    @GetMapping
    @Operation(summary = "상품 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공")
    })
    public ResponseEntity<List<GetProductsRes>> getProductList(GetProductsReq request) {

        List<GetProductsRes> productList = productService.getProductList(request);

        return ResponseEntity.ok()
                .header(GlobalConstant.CUSTOM_SERVER_PORT_HEADER, port)
                .body(productList);
    }

    /**
     * 상품 추가 API
     */
    @PostMapping
    @Operation(summary = "상품 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터")
    })
    public ResponseEntity<AddProductRes> addProduct(@RequestBody @Valid AddProductReq request) {

        AddProductRes response = productService.addProduct(request);

        return ResponseEntity.ok()
                .header(GlobalConstant.CUSTOM_SERVER_PORT_HEADER, port)
                .body(response);
    }
}
