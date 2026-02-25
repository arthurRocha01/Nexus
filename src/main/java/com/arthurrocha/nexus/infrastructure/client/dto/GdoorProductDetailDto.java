package com.arthurrocha.nexus.infrastructure.client.dto;

import java.math.BigDecimal;
import java.util.List;

public record GdoorProductDetailDto(
    Long id,
    String description,
    String reference,
    Integer current_quantity,
    Integer minimum_quantity,
    Integer reserved_quantity,
    BigDecimal price,
    BigDecimal wholesale_price,
    Integer wholesale_quantity,
    Integer validity_days,
    BigDecimal cost_price,
    BigDecimal average_cost,
    BigDecimal profit_margin,
    BigDecimal net_weight,
    BigDecimal gross_weight,
    Integer origin,
    String ncm_code,
    String ex_ncm_code,
    String cest_code,
    String classification_code,
    String fci_code,
    String cest_ind_escala,
    String manufacturer_cnpj,
    List<GdoorBarcodeDto> barcodes,
    List<Object> tax_rule_relations,
    List<Object> kits,
    List<Object> fuel_origins,
    Object anvisa,
    List<Object> serial_numbers
) {}
