package com.arthurrocha.nexus.infrastructure.client.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GdoorProductDetailDto {

    private Long id;
    private String description;
    private String reference;
    private Integer currentQuantity;
    private Integer minimumQuantity;
    private Integer reservedQuantity;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private Integer wholesaleQuantity;
    private Integer validityDays;
    private BigDecimal costPrice;
    private BigDecimal averageCost;
    private BigDecimal profitMargin;
    private BigDecimal netWeight;
    private BigDecimal grossWeight;
    private Integer origin;
    private String ncmCode;
    private String exNcmCode;
    private String cestCode;
    private String classificationCode;
    private String fciCode;
    private String cestIndEscala;
    private String manufacturerCnpj;
    private List<GdoorBarcodeDto> barcodes;
    private List<Object> taxRuleRelations;
    private List<Object> kits;
    private List<Object> fuelOrigins;
    private Object anvisa;
    private List<Object> serialNumbers;
}