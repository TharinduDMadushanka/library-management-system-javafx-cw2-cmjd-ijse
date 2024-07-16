package edu.ijse.dao.custom.impl;

import edu.ijse.dao.CrudUtil;
import edu.ijse.dao.custom.ReturnBookDao;
import edu.ijse.entity.ReturnBookEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReturnBookDaoImpl implements ReturnBookDao {
    @Override
    public boolean create(ReturnBookEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO returnbooks VALUES(?,?,?,?,?,?,?,?,?,?)",t.getReturnId(),t.getIssueId(),
                t.getBookId(),t.getBookDetails(),t.getMemberId(),t.getMemberDetails(),t.getIssueDate(),t.getDueDate(),t.getReturnDate(),t.getFine());
    }

    @Override
    public boolean update(ReturnBookEntity returnBookEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ReturnBookEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM returnbooks WHERE returnId=?", id);
        if(rst.next()){
            ReturnBookEntity returnBookEntity = new ReturnBookEntity(
                    rst.getString("returnId"),
                    rst.getString("issueId"),
                    rst.getString("bookId"),
                    rst.getString("bookDetails"),
                    rst.getString("memberId"),
                    rst.getString("memberDetails"),
                    rst.getDate("issueDate").toLocalDate(),
                    rst.getDate("dueDate").toLocalDate(),
                    rst.getDate("returnDate").toLocalDate(),
                    rst.getString("fine")
            );
            return returnBookEntity;
        }
        return null;
    }

    @Override
    public ArrayList<ReturnBookEntity> getAll() throws Exception {
        ArrayList<ReturnBookEntity> returnBookEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM returnbooks");

        while(rst.next()){
            ReturnBookEntity entity = new ReturnBookEntity(
                    rst.getString("returnId"),
                    rst.getString("issueId"),
                    rst.getString("bookId"),
                    rst.getString("bookDetails"),
                    rst.getString("memberId"),
                    rst.getString("memberDetails"),
                    rst.getDate("issueDate").toLocalDate(),
                    rst.getDate("dueDate").toLocalDate(),
                    rst.getDate("returnDate").toLocalDate(),
                    rst.getString("fine")
            );
            returnBookEntities.add(entity);
        }
        return returnBookEntities;
    }
}
