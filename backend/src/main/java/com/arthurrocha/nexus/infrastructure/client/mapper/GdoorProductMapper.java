package com.arthurrocha.nexus.infrastructure.client.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductDetailDto;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorProductDto;

import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

@Component
public class GdoorProductMapper {
    public Product toDomain(GdoorProductDto dto) {
    if (dto == null) {
        return null; 
    }

    Product.Builder builder = new Product.Builder(dto.id(), dto.name());

    List<GdoorProductDetailDto> details = dto.details();

    if (details == null || details.isEmpty()) {
        throw new IllegalStateException("Nenhum produto retornado pela API");
    }

    GdoorProductDetailDto detail = details.get(0);

    if (detail.current_quantity() != null) {
    builder.quantity(detail.current_quantity());
    }
    
    if (detail.price() != null) {
    builder.price(detail.price());
    }
    
    if (detail.ncm_code() != null) {
    builder.ncm(detail.ncm_code());
    }
    
    if (!detail.barcodes().isEmpty()) {
        builder.barcode(detail.barcodes().get(0).barcode());
    }

    return builder.build();
    }

    public ObjectNode fromDomain(ObjectNode dataNode, Product product) {
        updateRootFields(dataNode, product);
        updateDetails(dataNode, product);
        return dataNode;
    }

    private void updateRootFields(ObjectNode dataNode, Product product) {
        if (product.getDescription() != null) {
            dataNode.put("name", product.getDescription());
        }
    }

    private void updateDetails(ObjectNode dataNode, Product product) {
        if (!dataNode.has("details") || !dataNode.get("details").isArray()) return;
        
        ArrayNode detailsArray = (ArrayNode) dataNode.get("details");
        if (detailsArray.isEmpty()) return;

        ObjectNode firstDetail = (ObjectNode) detailsArray.get(0);
        
        if (product.getPrice() != null) {
            firstDetail.put("price", product.getPrice().doubleValue());
        }
        if (product.getQuantity() != null) {
            firstDetail.put("current_quantity", product.getQuantity());
        }
        if (product.getDescription() != null) {
            firstDetail.put("name", product.getDescription());
        }
        if (product.getNcm() != null) {
            firstDetail.put("ncm_code", product.getNcm());
        }

        updateBarcodes(firstDetail, product);
    }

    private void updateBarcodes(ObjectNode firstDetail, Product product) {
        if (product.getBarcode() == null) return;

        if (firstDetail.has("barcode") && firstDetail.get("barcode").isObject()) {
            ((ObjectNode) firstDetail.get("barcode")).put("barcode", product.getBarcode());
        }
        
        if (firstDetail.has("barcodes") && firstDetail.get("barcodes").isArray()) {
            ArrayNode barcodesList = (ArrayNode) firstDetail.get("barcodes");
            if (!barcodesList.isEmpty()) {
                ((ObjectNode) barcodesList.get(0)).put("barcode", product.getBarcode());
            }
        }
    }
}
