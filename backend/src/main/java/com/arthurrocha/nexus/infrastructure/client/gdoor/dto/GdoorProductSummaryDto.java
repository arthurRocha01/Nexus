package com.arthurrocha.nexus.infrastructure.client.gdoor.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GdoorProductSummaryDto {
    private String masterId;
    private String id;
    private String name;
    private Boolean hasGrid;
    private Boolean hasSerialNumbers;
    private String productType;
    private String measureUnit;
    private Integer detailsCount;
    private Integer totalQuantity;
    private BigDecimal avgPrice;
    private String obs;
    private Boolean active;
    private Boolean useComposition;
}
