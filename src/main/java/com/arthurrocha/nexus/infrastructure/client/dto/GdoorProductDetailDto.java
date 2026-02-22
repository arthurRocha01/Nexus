package com.arthurrocha.nexus.infrastructure.client.dto;

public record GdoorProductDetailDto(
  Double price,
  Integer currentQuantity,
  String ncmCode,
  GdoorBarcodeDto barcode
) {}
