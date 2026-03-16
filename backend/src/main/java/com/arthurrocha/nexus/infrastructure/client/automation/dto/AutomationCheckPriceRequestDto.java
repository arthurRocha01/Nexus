package com.arthurrocha.nexus.infrastructure.client.automation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutomationCheckPriceRequestDto  {
    AutomationProductDto data;
}