package com.arthurrocha.nexus.infrastructure.client.automation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AutomationProductBarcodeDto {
  private Integer id;
  private Integer masterId;
  private Integer productId;
  private String barcode;
  private Integer multiplier;
  private Boolean defaultValue;
}
