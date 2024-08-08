package com.sparta.msa_exam.product.domain.product.service;

import com.sparta.msa_exam.product.domain.product.dto.request.AddProductReq;
import com.sparta.msa_exam.product.domain.product.dto.request.GetProductsReq;
import com.sparta.msa_exam.product.domain.product.dto.response.AddProductRes;
import com.sparta.msa_exam.product.domain.product.dto.response.GetProductsRes;
import com.sparta.msa_exam.product.domain.product.entity.Product;
import com.sparta.msa_exam.product.domain.product.mapper.ProductMapper;
import com.sparta.msa_exam.product.domain.product.repository.ProductRepository;
import com.sparta.msa_exam.product.global.redis.CacheName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Cacheable(
            cacheNames = CacheName.PRODUCT_ALL,
            key = "{ #pageable.pageNumber, #pageable.pageSize }"
    )
    public List<GetProductsRes> getProductList(GetProductsReq request, Pageable pageable) {

        return productRepository.findAllBy(pageable)
                .map(ProductMapper.INSTANCE::toGetProductRes)
                .toList();
    }

    @Transactional
    @CacheEvict(cacheNames = CacheName.PRODUCT_ALL, allEntries = true) // 목록 조회 캐싱 삭제
    @CachePut(cacheNames = CacheName.PRODUCT, key = "#result.productId()") // 단일 캐싱 추가
    public AddProductRes addProduct(AddProductReq request) {

        Product product = Product.from(request);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.INSTANCE.toAddProductRes(savedProduct);
    }
}
