package edu.ijse.service.custom.impl;

import edu.ijse.dao.DaoFactory;
import edu.ijse.dao.custom.IssueBookDao;
import edu.ijse.dao.custom.ReturnBookDao;
import edu.ijse.db.DBConnection;
import edu.ijse.dto.ReturnBookDto;
import edu.ijse.entity.ReturnBookEntity;
import edu.ijse.service.custom.ReturnBookService;

import java.sql.Connection;

public class ReturnBookServiceImpl implements ReturnBookService {
    private IssueBookDao issueBookDao = (IssueBookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.ISSUE_BOOKS);
    private ReturnBookDao returnBookDao = (ReturnBookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.RETURN_BOOKS);

    @Override
    public String returnBook(ReturnBookDto returnBookDto) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            ReturnBookEntity returnBookEntity = new ReturnBookEntity(
                    returnBookDto.getReturnId(),
                    returnBookDto.getIssueId(),
                    returnBookDto.getBookId(),
                    returnBookDto.getBookDetails(),
                    returnBookDto.getMemberId(),
                    returnBookDto.getMemberDetails(),
                    returnBookDto.getIssueDate(),
                    returnBookDto.getDueDate(),
                    returnBookDto.getReturnDate(),
                    returnBookDto.getFine()
            );

            if (returnBookDao.create(returnBookEntity)) {
                if (issueBookDao.delete(returnBookDto.getIssueId())) {
                    connection.commit();
                    return "Return Book Saved Successfully..!";
                } else {
                    connection.rollback();
                    return "Return Book Saved Failed..! & Issue Book deletion failed..!";
                }
            } else {
                connection.rollback();
                return "Return Book Saved Failed..!";
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
