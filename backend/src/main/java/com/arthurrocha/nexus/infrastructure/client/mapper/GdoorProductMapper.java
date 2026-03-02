package com.arthurrocha.nexus.infrastructure.client.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductDetailDto;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductDto;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductSummaryDto;

@Component
public class GdoorProductMapper {
    public Product toDomain(GdoorProductDto dto) {
    if (dto == null) {
        return null; 
    }

    Product.Builder builder = new Product.Builder(dto.getId(), dto.getName());

    List<GdoorProductDetailDto> details = dto.getDetails();

    if (details == null || details.isEmpty()) {
        throw new IllegalStateException("Nenhum produto retornado pela API");
    }

    GdoorProductDetailDto detail = details.get(0);

    if (detail.getCurrentQuantity() != null) {
    builder.quantity(detail.getCurrentQuantity());
    }
    
    if (detail.getPrice() != null) {
    builder.price(detail.getPrice());
    }
    
    if (detail.getNcmCode() != null) {
    builder.ncm(detail.getNcmCode());
    }
    
    if (!detail.getBarcodes().isEmpty()) {
        builder.barcode(detail.getBarcodes().get(0).getBarcode());
    }

    return builder.build();
    }

    public Product toDomainFromSummary(GdoorProductSummaryDto summaryDto) {
        if (summaryDto == null) {
            return null;
        }

        Product.Builder builder = new Product.Builder(summaryDto.getId(), summaryDto.getName());

        if (summaryDto.getTotalQuantity() != null) {
            builder.quantity(summaryDto.getTotalQuantity());
        }

        if (summaryDto.getAvgPrice() != null) {
            builder.price(summaryDto.getAvgPrice());
        }

        return builder.build();
    }

    public void updateExternalFromDomain(Product product, GdoorProductDto externalDto) {
        if (product == null || externalDto == null) {
            return;
        }

        if(product.getDescription() != null) {
            externalDto.setName(product.getDescription());
        }

        List<GdoorProductDetailDto> details = externalDto.getDetails();

        if (details != null && !details.isEmpty()) {
            GdoorProductDetailDto detail = details.get(0);

            if (product.getPrice() != null) {
                detail.setPrice(product.getPrice());
            }

            if (product.getQuantity() != null) {
                detail.setCurrentQuantity(product.getQuantity());
            }

            if (product.getNcm() != null) {
                detail.setNcmCode(product.getNcm());
            }
        }
    }
}
