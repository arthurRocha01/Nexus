package com.arthurrocha.nexus.infrastructure.client.mapper;

import java.math.BigDecimal;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductDto;

public class GdoorProductMapper {
  
  public Product toDomain(GdoorProductDto dto) {
    if (dto == null) {
      return null;
    }

    return Product.create(String.valueOf(dto.id()), dto.name(), dto.totalQuantity(), new BigDecimal(dto.avgPrice()), "");
  }
}
