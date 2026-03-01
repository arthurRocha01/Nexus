package com.arthurrocha.nexus.infrastructure.client.dto;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GdoorBarcodeDto(
    Integer id,
    Integer master_id,
    Integer product_id,
    String barcode,
    Integer multiplier,
    Boolean _default
) {}