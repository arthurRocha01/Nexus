package com.arthurrocha.nexus.infrastructure.client.dto;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GdoorProductDto(
  String masterId,
  Long id,
  String name,
  Boolean hasGrid,
  Boolean hasSerialNumbers,
  String productType,
  String measureUnit,
  Integer detailsCount,
  Integer totalQuantity,
  Double avgPrice,
  String obs,
  Boolean active,
  Boolean useComposition
) {}
