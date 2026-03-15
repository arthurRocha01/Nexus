package com.arthurrocha.nexus.infrastructure.client.automation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AutomationAuthResponseDto {
    private String jwt_token;
    private String message;
}
