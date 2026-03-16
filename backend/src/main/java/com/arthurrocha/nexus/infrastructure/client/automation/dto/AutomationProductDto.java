package com.arthurrocha.nexus.infrastructure.client.automation.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutomationProductDto {
    private String id;
    private String description;
    private int quantity; 
    private BigDecimal price;
    private BigDecimal costPrice;
    private String ncm;
    private String barcode;
}
