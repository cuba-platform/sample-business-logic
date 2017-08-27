package com.company.sample.web.ex5.employee;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.EditAction;

import javax.inject.Named;
import java.util.Map;

public class EmployeeBrowse extends AbstractLookup {

    @Named("usersTable.edit")
    private EditAction usersTableEdit;

    @Override
    public void init(Map<String, Object> params) {
        usersTableEdit.setWindowId("sample$userEdit");
    }
}