package edu.ijse.entity;

import java.time.LocalDate;
import java.util.Date;

public class BookEntity {
    private String categoryId;
    private String bookId;
    private String title;
    private String author;
    private LocalDate publishYear;
    private String available;

    public BookEntity() {
    }

    public BookEntity(String categoryId, String bookId, String title, String author, LocalDate publishYear, String available) {
        this.categoryId = categoryId;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.available = available;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(LocalDate publishYear) {
        this.publishYear = publishYear;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "categoryId='" + categoryId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", available='" + available + '\'' +
                '}';
    }
}
