package edu.ijse.service;

import edu.ijse.service.custom.CategoryService;
import edu.ijse.service.custom.impl.*;

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
            case BOOKS:
                return new BookServiceImpl();
            case MEMBERS:
                return new MemberServiceImpl();
            case ISSUE_BOOKS:
                return new IssueBookServiceImpl();
            case RETURN_BOOKS:
                return new ReturnBookServiceImpl();
            default:
                return null;
        }
    }

    public enum ServiceType {
        CATEGORY,
        BOOKS,
        MEMBERS,
        ISSUE_BOOKS,
        RETURN_BOOKS
    }
}
