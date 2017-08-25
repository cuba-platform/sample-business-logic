package com.company.sample.core.jmx;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Discounts JMX bean interface.
 */
@ManagedResource(description = "Calculates customer discounts")
public interface DiscountsMBean {

    @ManagedOperation(description = "Recalculates discounts for all customers")
    String calculateDiscounts();

    @ManagedOperation(description = "Recalculates discount for a given customer")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "customerId", description = "customer id, like 1797f54d-5bec-87a6-4330-d958955743a2")})
    String calculateDiscount(String customerId);
}
