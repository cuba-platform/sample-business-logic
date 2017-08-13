package com.company.sample.web.ex1.customer;

import com.company.sample.entity.Order;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.sample.entity.Customer;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class CustomerEdit extends AbstractEditor<Customer> {

    @Inject
    private Datasource<Customer> customerDs;

    @Inject
    private DataManager dataManager;

    /**
     * This method is called when the user clicks on the "Calculate discount" button.
     */
    public void onCalcDiscountBtnClick() {
        // Calculate a discount for the edited customer
        Customer customer = customerDs.getItem();
        BigDecimal discount = calculateDiscount(customer);

        // Set the discount for the customer. It will be saved when the user commits the editor by clicking "OK".
        customer.setDiscount(discount);
    }

    /**
     * Calculates discount for a given customer based on the total sum of its orders.
     */
    private BigDecimal calculateDiscount(Customer customer) {
        // Load the list of orders (keep in mind that this approach is far from being efficient)
        LoadContext<Order> loadContext = LoadContext.create(Order.class).setQuery(
                LoadContext.createQuery("select e from sample$Order e where e.customer.id = :custId")
                        .setParameter("custId", customer.getId())
        );
        List<Order> orders = dataManager.loadList(loadContext);

        // Calculate the total sum
        BigDecimal sum = orders.stream().map(Order::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

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