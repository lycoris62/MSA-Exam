package com.sparta.msa_exam.product.domain.product.mapper;

import com.sparta.msa_exam.product.domain.product.dto.response.AddProductRes;
import com.sparta.msa_exam.product.domain.product.entity.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ProductMapper {

    AddProductRes toAddProductRes(Product product);
}
