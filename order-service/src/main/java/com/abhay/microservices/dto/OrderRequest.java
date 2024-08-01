package com.abhay.microservices.dto;

import com.abhay.microservices.model.Order;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {

}
