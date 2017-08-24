package com.company.sample.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface DiscountService {
    String NAME = "sample_DiscountService";

    BigDecimal calculateDiscount(UUID customerId);
}