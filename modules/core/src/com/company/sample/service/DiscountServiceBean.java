package com.company.sample.service;

import com.company.sample.core.DiscountCalculator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service(DiscountService.NAME)
public class DiscountServiceBean implements DiscountService {

    @Inject
    private DiscountCalculator discountCalculator;

    @Override
    @Transactional
    public BigDecimal calculateDiscount(UUID customerId) {
        // You could write some business logic right here or delegate to a separate managed bean
        // of the middle tier to share code with other services, entity listeners and JMX beans
        return discountCalculator.calculateDiscount(customerId);
    }
}