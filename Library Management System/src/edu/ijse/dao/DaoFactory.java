package edu.ijse.dao;

import edu.ijse.dao.custom.CategoryDao;
import edu.ijse.dao.custom.impl.CategoryDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoType type) {
        switch (type) {
            case CATEGORY:
                return new CategoryDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoType{
        CATEGORY
    }
}
