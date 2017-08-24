package com.company.sample.listener;

import com.company.sample.entity.Customer;
import com.company.sample.entity.Order;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.listener.BeforeDeleteEntityListener;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("sample_OrderEntityListener")
public class OrderEntityListener implements BeforeDeleteEntityListener<Order>, BeforeInsertEntityListener<Order>, BeforeUpdateEntityListener<Order> {

    @Override
    public void onBeforeDelete(Order entity, EntityManager entityManager) {
        calculateDiscounts(entity.getCustomer(), entityManager);
    }

    @Override
    public void onBeforeInsert(Order entity, EntityManager entityManager) {
        calculateDiscounts(entity.getCustomer(), entityManager);
    }

    @Override
    public void onBeforeUpdate(Order entity, EntityManager entityManager) {
        calculateDiscounts(entity.getCustomer(), entityManager);
    }

    private void calculateDiscounts(Customer customer, EntityManager entityManager) {
        // Calculate the total amount on the database level - the fastest possible way
        Query query = entityManager.createQuery(
                "select sum(e.amount) from sample$Order e where e.customer.id = :customerId");
        query.setParameter("customerId", customer.getId());
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

        // Set the discount for the customer. It will be saved on transaction commit.
        customer.setDiscount(discount);
    }
}