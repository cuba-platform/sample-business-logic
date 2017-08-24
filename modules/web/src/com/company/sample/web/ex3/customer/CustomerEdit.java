package com.company.sample.web.ex3.customer;

import com.company.sample.entity.Customer;
import com.company.sample.service.DiscountService;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.math.BigDecimal;

public class CustomerEdit extends AbstractEditor<Customer> {

    @Inject
    private Datasource<Customer> customerDs;

    @Inject
    private DiscountService discountService;

    /**
     * This method is called when the user clicks on the "Calculate discount" button.
     */
    public void onCalcDiscountBtnClick() {
        // Calculate a discount for the edited customer
        Customer customer = customerDs.getItem();
        BigDecimal discount = discountService.calculateDiscount(customer.getId());

        // Set the discount for the customer. It will be saved when the user commits the editor by clicking "OK".
        customer.setDiscount(discount);
    }
}