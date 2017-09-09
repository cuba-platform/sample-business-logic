package com.company.sample.listener;

import com.company.sample.core.DiscountCalculator;
import com.company.sample.entity.Customer;
import com.company.sample.entity.Order;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.listener.BeforeDeleteEntityListener;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;

@Component("sample_OrderEntityListener")
public class OrderEntityListener implements BeforeDeleteEntityListener<Order>, BeforeInsertEntityListener<Order>, BeforeUpdateEntityListener<Order> {

    @Inject
    private DiscountCalculator discountCalculator;

    @Override
    public void onBeforeDelete(Order entity, EntityManager entityManager) {
        calculateDiscount(entity.getCustomer(), entityManager);
    }

    @Override
    public void onBeforeInsert(Order entity, EntityManager entityManager) {
        calculateDiscount(entity.getCustomer(), entityManager);
    }

    @Override
    public void onBeforeUpdate(Order entity, EntityManager entityManager) {
        calculateDiscount(entity.getCustomer(), entityManager);
    }

    private void calculateDiscount(Customer customer, EntityManager entityManager) {
        // Delegate calculation to a managed bean of the middle tier
        BigDecimal discount = discountCalculator.calculateDiscount(customer.getId());

        // Merge customer instance because it comes here as part of another entity's object graph and can be detached
        Customer managedCustomer = entityManager.merge(customer);

        // Set the discount for the customer. It will be saved on transaction commit.
        managedCustomer.setDiscount(discount);
    }
}