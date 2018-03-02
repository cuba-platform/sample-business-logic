package com.company.sample.core;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Managed bean of the middle tier providing calculation of customer discounts.
 */
@Component("sample_DiscountCalculator")
public class DiscountCalculator {

    @Inject
    private Persistence persistence;

    /**
     * Calculates discount for a given customer. Expects that an active transaction exists at the moment.
     * @param customerId customer id
     * @return discount value
     */
    public BigDecimal calculateDiscount(UUID customerId) {
        // Calculate the total amount on the database level - the fastest possible way
        Query query = persistence.getEntityManager().createQuery(
                "select sum(e.amount) from sample$Order e where e.customer.id = :custId");
        query.setParameter("custId", customerId);
        BigDecimal sum = (BigDecimal) query.getSingleResult();
        if (sum == null)
            sum = BigDecimal.ZERO;

        // Decide about discount
        BigDecimal discount = BigDecimal.ZERO;
        if (sum.intValue() > 300)
            discount = new BigDecimal("30");
        else if (sum.intValue() > 200)
            discount = new BigDecimal("20");
        else if (sum.intValue() > 100)
            discount = new BigDecimal("10");
        // Add some randomness to always get a new value for the demonstration purpose
        discount = discount.multiply(new BigDecimal(Math.random()));

        return discount;
    }
}
