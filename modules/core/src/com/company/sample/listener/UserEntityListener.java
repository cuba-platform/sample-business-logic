package com.company.sample.listener;

import com.company.sample.entity.Employee;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("sample_UserEntityListener")
public class UserEntityListener implements BeforeUpdateEntityListener<User> {

    @Inject
    private Persistence persistence;

    @Override
    public void onBeforeUpdate(User entity, EntityManager entityManager) {
        // If the user's "name" attribute has been changed
        if (persistence.getTools().getDirtyFields(entity).contains("name")) {
            // Find a linked employee
            TypedQuery<Employee> query = entityManager.createQuery(
                    "select e from sample$Employee e where e.user.id = ?1", Employee.class);
            query.setParameter(1, entity.getId());
            Employee employee = query.getFirstResult();
            if (employee != null) {
                // and change its name as well
                employee.setName(entity.getName());
            }
        }
    }
}