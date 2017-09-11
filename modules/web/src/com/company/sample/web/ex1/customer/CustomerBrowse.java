package com.company.sample.web.ex1.customer;

import com.company.sample.entity.Customer;
import com.company.sample.entity.Order;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CustomerBrowse extends AbstractLookup {

    @Inject
    private GroupDatasource<Customer, UUID> customersDs;

    @Inject
    private DataManager dataManager;

    /**
     * This method is called when the user executes the "calcDiscount" action via the table context menu or by clicking
     * on the corresponding button.
     */
    public void onCalcDiscount(Component source) {
        // Calculate a discount for the customer currently selected in the table
        Customer selectedCustomer = customersDs.getItem();
        if (selectedCustomer == null) {
            showNotification("Select a customer");
            return;
        }
        BigDecimal discount = calculateDiscount(selectedCustomer);

        // Set the discount for the customer and save it
        selectedCustomer.setDiscount(discount);
        dataManager.commit(selectedCustomer);

        // Refresh the datasource to display results in the table
        customersDs.refresh();

        showNotification("Done", NotificationType.TRAY);
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

    // The code below is not relevant to the example.
    // It makes actions invoke the editor screen with a name not following the convention ({entityName}.edit).

    @Named("customersTable.create")
    private CreateAction customersTableCreate;

    @Named("customersTable.edit")
    private EditAction customersTableEdit;

    @Override
    public void init(Map<String, Object> params) {
        customersTableCreate.setWindowId("sample$Customer.edit_1");
        customersTableEdit.setWindowId("sample$Customer.edit_1");
    }
}