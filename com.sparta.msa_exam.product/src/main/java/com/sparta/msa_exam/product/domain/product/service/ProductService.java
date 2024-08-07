package com.sparta.msa_exam.product.domain.product.service;

import com.sparta.msa_exam.product.domain.product.dto.request.AddProductReq;
import com.sparta.msa_exam.product.domain.product.dto.response.AddProductRes;
import com.sparta.msa_exam.product.domain.product.entity.Product;
import com.sparta.msa_exam.product.domain.product.mapper.ProductMapper;
import com.sparta.msa_exam.product.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository productRepository;

    @Transactional
    public AddProductRes addProduct(AddProductReq request) {

        Product product = Product.builder()
                .name(request.name())
                .supplyPrice(request.supplyPrice())
                .build();

        Product savedProduct = productRepository.save(product);

        return mapper.toAddProductRes(savedProduct);
    }
}
