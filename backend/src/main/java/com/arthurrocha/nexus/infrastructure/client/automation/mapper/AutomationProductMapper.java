package com.arthurrocha.nexus.infrastructure.client.automation.mapper;

import org.springframework.stereotype.Component;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.domain.ProductMatchResult;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationCheckPriceRequestDto;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationCheckPriceResponseDto;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationProductDto;

@Component
public class AutomationProductMapper {
    
    public AutomationCheckPriceRequestDto toRequestDto(Product product) {
        if (product == null) {
            return null;
        }
        
        AutomationProductDto productData = AutomationProductDto.builder()
        .id(product.getId())
        .description(product.getDescription())
        .quantity(product.getQuantity())
        .price(product.getPrice())
        .costPrice(product.getCostPrice())
        .ncm(product.getNcm())
        .barcode(product.getBarcode())
        .build();

        return AutomationCheckPriceRequestDto.builder()
        .data(productData)
        .build();
    }
    
    public ProductMatchResult toMatchResult(AutomationCheckPriceResponseDto dto, Product originalProduct) {
        if (dto == null) {
            return null;
        }

        Product matchedProduct = this.toDomain(dto.getCorrespondingProduct());
        
        return new ProductMatchResult(
            originalProduct,
            matchedProduct,
            dto.getSimilarityScore()
        );
    }

    private Product toDomain(AutomationProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product.Builder builder = new Product.Builder(dto.getId(), dto.getDescription());
        builder.quantity(dto.getQuantity());
        builder.price(dto.getPrice());
        builder.costPrice(dto.getCostPrice());
        builder.ncm(dto.getNcm());
        builder.barcode(dto.getBarcode());

        return builder.build();
    }
}
