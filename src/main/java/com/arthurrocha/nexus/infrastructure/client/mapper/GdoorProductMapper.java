package com.arthurrocha.nexus.infrastructure.client.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductDetailDto;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductDto;

@Component
public class GdoorProductMapper {
    public Product toDomain(GdoorProductDto dto) {
    if (dto == null) {
        return null; 
    }

    Product.Builder builder = new Product.Builder(String.valueOf(dto.id()), dto.name());

    List<GdoorProductDetailDto> details = dto.details();

    if (details != null && !details.isEmpty()) {
        GdoorProductDetailDto detail = details.get(0);
        
        if (detail.currentQuantity() != null) {
        builder.quantity(detail.currentQuantity());
        }
        
        if (detail.price() != null) {
        builder.price(BigDecimal.valueOf(detail.price()));
        }
        
        if (detail.ncmCode() != null) {
        builder.ncm(detail.ncmCode());
        }
        
        if (detail.barcode() != null && detail.barcode().barcode() != null) {
        builder.barcode(detail.barcode().barcode());
        };
    }

    return builder.build();
    }
}
