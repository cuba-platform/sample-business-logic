package com.company.sample.web.ex2;

import com.company.sample.entity.Customer;
import com.company.sample.entity.Order;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Managed bean of the client tier encapsulating logic of calculating discounts.
 */
@Component("sample_DiscountCalculator")
public class DiscountCalculator {

    @Inject
    private DataManager dataManager;

    /**
     * Calculates discount for a given customer based on the total sum of its orders.
     */
    public BigDecimal calculateDiscount(Customer customer) {
        // Load the list of orders (keep in mind that this approach is far from being efficient)
        LoadContext<Order> loadContext = LoadContext.create(Order.class).setQuery(
                LoadContext.createQuery("select e from sample$Order e where e.customer.id = :custId")
                        .setParameter("custId", customer.getId())
        );
        List<Order> orders = dataManager.loadList(loadContext);

        // Calculate the total sum
        BigDecimal sum = orders.stream()
                .map(order -> order.getAmount() != null ? order.getAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

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
