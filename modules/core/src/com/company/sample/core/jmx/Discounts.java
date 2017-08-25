package com.company.sample.core.jmx;

import com.company.sample.core.DiscountCalculator;
import com.company.sample.entity.Customer;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.security.app.Authenticated;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Discounts JMX bean implementation.
 */
@Component("sample_DiscountsMBean")
public class Discounts implements DiscountsMBean {

    private Logger log = LoggerFactory.getLogger(Discounts.class);

    @Inject
    private Persistence persistence;

    @Inject
    private DiscountCalculator discountCalculator;

    @Override
    @Authenticated
    public String calculateDiscounts() {
        try {
            List<Customer> customers;
            try (Transaction tx = persistence.createTransaction()) {
                TypedQuery<Customer> query = persistence.getEntityManager().createQuery(
                        "select c from sample$Customer c", Customer.class);
                customers = query.getResultList();
                for (Customer customer : customers) {
                    BigDecimal discount = discountCalculator.calculateDiscount(customer.getId());
                    customer.setDiscount(discount);
                }
                tx.commit();
            }
            return "Updated discounts for " + customers.size() + " customers";
        } catch (Throwable e) {
            log.error("Error calculating discounts", e);
            return ExceptionUtils.getFullStackTrace(e);
        }
    }

    @Override
    @Authenticated
    public String calculateDiscount(String customerId) {
        if (customerId == null) {
            return "Enter a customer id";
        }
        try {
            UUID uuid = UUID.fromString(customerId);
            try (Transaction tx = persistence.createTransaction()) {
                Customer customer = persistence.getEntityManager().find(Customer.class, uuid);
                if (customer == null) {
                    return "Customer with id " + customerId + " does not exist";
                }
                BigDecimal discount = discountCalculator.calculateDiscount(uuid);
                customer.setDiscount(discount);
                tx.commit();
            }
            return "Updated discount for customer " + customerId;
        } catch (Throwable e) {
            log.error("Error calculating discount", e);
            return ExceptionUtils.getFullStackTrace(e);
        }
    }
}
