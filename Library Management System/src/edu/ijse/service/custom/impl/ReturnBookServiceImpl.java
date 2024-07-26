package edu.ijse.service.custom.impl;

import edu.ijse.dao.DaoFactory;
import edu.ijse.dao.custom.IssueBookDao;
import edu.ijse.dao.custom.ReturnBookDao;
import edu.ijse.db.DBConnection;
import edu.ijse.dto.ReturnBookDto;
import edu.ijse.entity.ReturnBookEntity;
import edu.ijse.service.custom.ReturnBookService;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;

public class ReturnBookServiceImpl implements ReturnBookService {
    private IssueBookDao issueBookDao = (IssueBookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.ISSUE_BOOKS);
    private ReturnBookDao returnBookDao = (ReturnBookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.RETURN_BOOKS);

    @Override
    public String returnBook(ReturnBookDto returnBookDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            ArrayList<ReturnBookEntity> returnBookForIssue = returnBookDao.getAll();

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

            if (!returnBookDao.create(returnBookEntity)) {
                connection.rollback();
                return "Return Book saved Failed..!";
            }

            // Now delete issue book entry
//            if (issueBookDao.delete(returnBookDto.getIssueId())) {
//                connection.commit();
//                return "Return Book Saved Successfully..!";
//            } else {
//                connection.rollback();
//                return "Return Book Saved Failed..! & Issue Book deletion failed..!";
//            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return "Return Book Successfully..!";
    }

    @Override
    public ArrayList<ReturnBookDto> getAll() throws Exception {
        ArrayList<ReturnBookEntity> returnBookEntities = returnBookDao.getAll();

        if (returnBookEntities != null && !returnBookEntities.isEmpty()) {
            ArrayList<ReturnBookDto> returnBookDtos = new ArrayList<>();
            for (ReturnBookEntity returnBookEntity : returnBookEntities) {
                returnBookDtos.add(new ReturnBookDto(
                        returnBookEntity.getReturnId(),
                        returnBookEntity.getIssueId(),
                        returnBookEntity.getBookId(),
                        returnBookEntity.getBookDetails(),
                        returnBookEntity.getMemberId(),
                        returnBookEntity.getMemberDetails(),
                        returnBookEntity.getIssueDate(),
                        returnBookEntity.getDueDate(),
                        returnBookEntity.getReturnDate(),
                        returnBookEntity.getFine()
                ));
            }
            return returnBookDtos;
        }
        return null;
    }

    @Override
    public ReturnBookDto get(String id) throws Exception {
        ReturnBookEntity entity = returnBookDao.get(id);
        if (entity != null) {
            return new ReturnBookDto(
                    entity.getReturnId(),
                    entity.getIssueId(),
                    entity.getBookId(),
                    entity.getBookDetails(),
                    entity.getMemberId(),
                    entity.getMemberDetails(),
                    entity.getIssueDate(),
                    entity.getDueDate(),
                    entity.getReturnDate(),
                    entity.getFine()
            );
        }
        return null;
    }
}
