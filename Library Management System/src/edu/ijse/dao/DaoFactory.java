package edu.ijse.dao;

import edu.ijse.dao.custom.CategoryDao;
import edu.ijse.dao.custom.impl.*;

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
            case ISSUE_BOOKS:
                return new IssueBookDaoImpl();
            case RETURN_BOOKS:
                return new ReturnBookDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoType{
        CATEGORY,
        BOOKS,
        MEMBERS,
        ISSUE_BOOKS,
        RETURN_BOOKS
    }
}
