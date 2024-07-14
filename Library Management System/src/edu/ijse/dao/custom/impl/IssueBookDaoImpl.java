package edu.ijse.dao.custom.impl;

import edu.ijse.dao.CrudUtil;
import edu.ijse.dao.custom.IssueBookDao;
import edu.ijse.entity.IssueBookEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class IssueBookDaoImpl implements IssueBookDao {
    @Override
    public boolean create(IssueBookEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO issuebooks VALUES(?,?,?,?,?,?,?)", t.getIssueId(), t.getBookId(), t.getBookDetails(),
                t.getMemberId(), t.getMemberDetails(), t.getIssueDate(), t.getDueDate());
    }

    @Override
    public boolean update(IssueBookEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE issuebooks SET bookId=?,bookDetails=?,memberId=?,memberDetails=?,issueDate=?,dueDate=? WHERE issueId=?",
                t.getBookId(), t.getBookDetails(), t.getMemberId(), t.getMemberDetails(), t.getIssueDate(), t.getDueDate(), t.getIssueId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM issuebooks WHERE issueId=?", id);
    }

    @Override
    public IssueBookEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM issuebooks WHERE issueId=?", id);
        if (rst.next()) {
            IssueBookEntity issueBookEntity = new IssueBookEntity(
                    rst.getString("issueId"),
                    rst.getString("bookId"),
                    rst.getString("bookDetails"),
                    rst.getString("memberId"),
                    rst.getString("memberDetails"),
                    rst.getDate("issueDate").toLocalDate(),
                    rst.getDate("dueDate").toLocalDate()
            );
            return issueBookEntity;
        }
        return null;
    }

    @Override
    public ArrayList<IssueBookEntity> getAll() throws Exception {
        ArrayList<IssueBookEntity> issueBookEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM issuebooks");

        while (rst.next()) {
            IssueBookEntity entity = new IssueBookEntity(
                    rst.getString("issueId"),
                    rst.getString("bookId"),
                    rst.getString("bookDetails"),
                    rst.getString("memberId"),
                    rst.getString("memberDetails"),
                    rst.getDate("issueDate").toLocalDate(),
                    rst.getDate("dueDate").toLocalDate()
            );
            issueBookEntities.add(entity);
        }
        return issueBookEntities;
    }
}
