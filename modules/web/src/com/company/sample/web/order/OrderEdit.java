package com.company.sample.web.order;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.sample.entity.Order;
import com.haulmont.cuba.gui.components.LookupPickerField;

import javax.inject.Named;
import java.util.Map;

public class OrderEdit extends AbstractEditor<Order> {

    @Named("fieldGroup.customer")
    private LookupPickerField customerField;

    @Override
    public void init(Map<String, Object> params) {
        customerField.removeAllActions();
    }
}