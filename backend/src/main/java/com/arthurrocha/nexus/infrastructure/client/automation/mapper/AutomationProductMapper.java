package com.arthurrocha.nexus.infrastructure.client.automation.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationCheckPriceRequestDto;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationProductDetailsDto;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationProductDto;

@Component
public class AutomationProductMapper {
  
  public Product toDomain(AutomationProductDto dto) {
    if (dto == null) {
      return null;
    }

    Product.Builder builder = new Product.Builder(dto.getId(), dto.getName());

    List<AutomationProductDetailsDto> details = dto.getDetails();

    if (details == null || details.isEmpty()) {
      throw new IllegalStateException("Nenhum produto retornado pela API");
    }

    AutomationProductDetailsDto detail = details.get(0);

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
}
