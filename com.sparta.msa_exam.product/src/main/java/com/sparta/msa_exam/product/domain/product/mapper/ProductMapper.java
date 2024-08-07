package com.sparta.msa_exam.product.domain.product.mapper;

import com.sparta.msa_exam.product.domain.product.dto.response.AddProductRes;
import com.sparta.msa_exam.product.domain.product.dto.response.GetProductRes;
import com.sparta.msa_exam.product.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    GetProductRes toGetProductRes(Product product);

    AddProductRes toAddProductRes(Product product);
}
