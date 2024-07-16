package edu.ijse.controller;

import edu.ijse.dto.ReturnBookDto;
import edu.ijse.service.ServiceFactory;
import edu.ijse.service.custom.ReturnBookService;

public class ReturnBookController {
    private ReturnBookService returnBookService = (ReturnBookService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.RETURN_BOOKS);

    public String returnBook(ReturnBookDto returnBookDto) throws Exception {
        return returnBookService.returnBook(returnBookDto);
    }
}
