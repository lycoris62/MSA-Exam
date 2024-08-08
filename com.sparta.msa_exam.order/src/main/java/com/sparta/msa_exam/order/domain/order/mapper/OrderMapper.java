package com.sparta.msa_exam.order.domain.order.mapper;

import com.sparta.msa_exam.order.domain.order.dto.response.CreateOrderRes;
import com.sparta.msa_exam.order.domain.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(
            target = "productIds",
            expression = "java(order.getOrderItemList().stream().map(orderItem -> orderItem.getProductId()).toList())"
    )
    CreateOrderRes toCreateOrderRes(Order order);
}
