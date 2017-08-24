package com.company.sample.service;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service(DiscountService.NAME)
public class DiscountServiceBean implements DiscountService {

    @Inject
    private Persistence persistence;

    @Override
    @Transactional
    public BigDecimal calculateDiscount(UUID customerId) {
        // Calculate the total amount on the database level - the fastest possible way
        Query query = persistence.getEntityManager().createQuery(
                "select sum(e.amount) from sample$Order e where e.customer.id = :custId");
        query.setParameter("custId", customerId);
        BigDecimal sum = (BigDecimal) query.getSingleResult();

        // Decide about discount
        BigDecimal discount = BigDecimal.ZERO;
        if (sum.intValue() > 100)
            discount = new BigDecimal("10");
        else if (sum.intValue() > 200)
            discount = new BigDecimal("20");
        else if (sum.intValue() > 300)
            discount = new BigDecimal("30");
        // Add some randomness to always get a new value for the demonstration purpose
        discount = discount.multiply(new BigDecimal(Math.random()));

        return discount;
    }
}