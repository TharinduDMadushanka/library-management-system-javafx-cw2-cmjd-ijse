package edu.ijse.controller;

import edu.ijse.dto.BookDto;
import edu.ijse.service.ServiceFactory;
import edu.ijse.service.custom.BookService;

import java.util.ArrayList;

public class BookController {
    private BookService bookService = (BookService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BOOKS);

    public String save(BookDto bookDto) throws Exception {
        return bookService.save(bookDto);
    }

    public String update(BookDto bookDto) throws Exception {
        return bookService.update(bookDto);
    }

    public String delete(String bookId) throws Exception {
        return bookService.delete(bookId);
    }

    public ArrayList<BookDto> getAllBooks() throws Exception {
        return bookService.getAll();
    }

    public BookDto get(String bookId) throws Exception {
        return bookService.get(bookId);
    }
}
