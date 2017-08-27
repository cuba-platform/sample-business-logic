package com.company.sample.core;

import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.listener.EntityListenerManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Registers an entity listener for the User entity on application startup.
 */
@Component("sample_AppLifecycle")
public class AppLifecycle implements AppContext.Listener {

    @Inject
    private EntityListenerManager entityListenerManager;

    public AppLifecycle() {
        // Add itself to the collection of lifecycle listeners
        AppContext.addListener(this);
    }

    /**
     * Invoked by the framework on application startup.
     */
    @Override
    public void applicationStarted() {
        entityListenerManager.addListener(User.class, "sample_UserEntityListener");
    }

    /**
     * Invoked by the framework on application shutdown.
     */
    @Override
    public void applicationStopped() {
    }
}
