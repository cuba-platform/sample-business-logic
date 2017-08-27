package com.company.sample.web.ex5.employee;

import com.company.sample.entity.Employee;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.PickerField;

import javax.inject.Named;
import java.util.Map;

public class EmployeeEdit extends AbstractEditor<Employee> {

    @Named("fieldGroup.user")
    private PickerField userField;

    @Override
    public void init(Map<String, Object> params) {
        userField.getOpenAction().setEditScreen("sample$userEdit");
    }
}