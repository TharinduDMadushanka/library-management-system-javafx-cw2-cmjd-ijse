package edu.ijse.dao;

import edu.ijse.dao.custom.CategoryDao;
import edu.ijse.dao.custom.impl.BookDaoImpl;
import edu.ijse.dao.custom.impl.CategoryDaoImpl;
import edu.ijse.dao.custom.impl.MemberDaoImpl;

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
            case BOOKS:
                return new BookDaoImpl();
            case MEMBERS:
                return new MemberDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoType{
        CATEGORY,
        BOOKS,
        MEMBERS
    }
}
