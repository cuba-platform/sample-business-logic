package com.company.sample.web.ex2.customer;

import com.company.sample.entity.Customer;
import com.company.sample.web.ex2.DiscountCalculator;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class CustomerBrowse extends AbstractLookup {

    @Inject
    private GroupDatasource<Customer, UUID> customersDs;

    @Inject
    private DataManager dataManager;

    @Inject
    private DiscountCalculator discountCalculator;

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
        BigDecimal discount = discountCalculator.calculateDiscount(selectedCustomer);

        // Set the discount for the customer and save it
        selectedCustomer.setDiscount(discount);
        dataManager.commit(selectedCustomer);

        // Refresh the datasource to display results in the table
        customersDs.refresh();

        showNotification("Done", NotificationType.TRAY);
    }

    // The code below is not relevant to the example.
    // It makes actions invoke the editor screen with a name not following the convention ({entityName}.edit).

    @Named("customersTable.create")
    private CreateAction customersTableCreate;

    @Named("customersTable.edit")
    private EditAction customersTableEdit;

    @Override
    public void init(Map<String, Object> params) {
        customersTableCreate.setWindowId("sample$Customer.edit_2");
        customersTableEdit.setWindowId("sample$Customer.edit_2");
    }
}