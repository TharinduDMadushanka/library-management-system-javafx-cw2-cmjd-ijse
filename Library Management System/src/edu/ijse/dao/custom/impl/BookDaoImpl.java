package edu.ijse.dao.custom.impl;

import edu.ijse.dao.CrudUtil;
import edu.ijse.dao.custom.BookDao;
import edu.ijse.entity.BookEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean create(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO books VALUES(?,?,?,?,?,?)", t.getCategoryId(), t.getBookId(), t.getTitle(),
                t.getAuthor(), t.getPublishYear(), t.getAvailable());
    }

    @Override
    public boolean update(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE books SET categoryId = ?,title = ?,author=?,publishYear=?,available=? WHERE bookId=?",
                t.getCategoryId(), t.getTitle(), t.getAuthor(), t.getPublishYear(), t.getAvailable(), t.getBookId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM books WHERE bookId=?", id);
    }

    @Override
    public BookEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM books WHERE bookId=?", id);
        if (rst.next()) {
            BookEntity entity = new BookEntity(
                    rst.getString("categoryId"),
                    rst.getString("bookId"),
                    rst.getString("title"),
                    rst.getString("author"),
                    rst.getDate("publishYear").toLocalDate(),
                    rst.getString("available")
            );
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BookEntity> getAll() throws Exception {
        ArrayList<BookEntity> bookEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM books");
        while (rst.next()) {
            BookEntity entity = new BookEntity(
                    rst.getString("categoryId"),
                    rst.getString("bookId"),
                    rst.getString("title"),
                    rst.getString("author"),
                    rst.getDate("publishYear").toLocalDate(),
                    rst.getString("available")
            );
            bookEntities.add(entity);
        }
        return bookEntities;
    }
}
