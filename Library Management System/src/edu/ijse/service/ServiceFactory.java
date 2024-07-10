package edu.ijse.service;

import edu.ijse.service.custom.CategoryService;
import edu.ijse.service.custom.impl.CategoryServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getService(ServiceType type) {
        switch (type) {
            case CATEGORY:
                return new CategoryServiceImpl();
            default:
                return null;
        }
    }

    public enum ServiceType {
        CATEGORY
    }
}
