package com.arthurrocha.nexus.infrastructure.client.gdoor.dto;

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
public class GdoorProductDto {
    private String id;
    private String name;
    private String productType; 
    private String measureUnit;
    private Integer commission;
    private String ippt;
    private Boolean active;
    private Boolean ghubSync;
    private String obs;
    private Boolean hasGrid;
    private Boolean hasWeigth;
    private Boolean hasSerialNumbers;
    private List<Object> units;
    private List<GdoorProductDetailDto> details;
    private Boolean useComposition;
    private Object composition;
}