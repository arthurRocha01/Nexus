package com.arthurrocha.nexus.infrastructure.client.dto;

import java.util.List;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GdoorProductDto(
    String id,
    String name,
    String product_type,
    String measure_unit,
    Integer commission,
    String ippt,
    Boolean active,
    Boolean ghub_sync,
    String obs,
    Boolean has_grid,
    Boolean has_weigth,
    Boolean has_serial_numbers,
    List<Object> units,
    List<GdoorProductDetailDto> details,
    Boolean use_composition,
    Object composition
) {}
