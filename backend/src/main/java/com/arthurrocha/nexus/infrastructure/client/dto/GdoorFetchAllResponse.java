package com.arthurrocha.nexus.infrastructure.client.dto;

import java.util.List;

public record GdoorFetchAllResponse(
  List<GdoorProductSummaryDto> data
) {}
