package com.arthurrocha.nexus.infrastructure.client.gdoor.dto;

import java.util.List;

public record GdoorFetchAllResponse(
  List<GdoorProductSummaryDto> data
) {}
