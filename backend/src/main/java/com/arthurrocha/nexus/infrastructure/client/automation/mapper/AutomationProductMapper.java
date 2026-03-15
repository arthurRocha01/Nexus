package com.arthurrocha.nexus.infrastructure.client.automation.mapper;

import org.springframework.stereotype.Component;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.domain.ProductMatchResult;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationCheckPriceRequestDto;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationCheckPriceResponseDto;

@Component
public class AutomationProductMapper {
    
    public AutomationCheckPriceRequestDto toRequestDto(Product product) {
        if (product == null) {
            return null;
        }
        
        return AutomationCheckPriceRequestDto.builder()
        .id(product.getId())
        .description(product.getDescription())
        .quantity(product.getQuantity())
        .price(product.getPrice())
        .ncm(product.getNcm())
        .barcode(product.getBarcode())
        .build();
    }
    
    public ProductMatchResult toMatchResult(AutomationCheckPriceResponseDto dto, Product originalProduct) {
        if (dto == null) {
            return null;
        }
        
        return new ProductMatchResult(
            originalProduct,
            dto.getCorrespondingProductName(),
            dto.getSimilarityScore()
        );
    }
}
