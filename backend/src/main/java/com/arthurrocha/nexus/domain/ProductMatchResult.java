package com.arthurrocha.nexus.domain;

import java.math.BigDecimal;

public record ProductMatchResult(
    Product originalProduct,
    Product matchedProduct,
    BigDecimal similarityScore
) {}
