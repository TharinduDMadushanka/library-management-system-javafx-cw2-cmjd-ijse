package edu.ijse.service.custom;

import edu.ijse.dto.ReturnBookDto;
import edu.ijse.service.SuperService;

import java.util.ArrayList;

public interface ReturnBookService extends SuperService {
    String returnBook(ReturnBookDto returnBookDto)throws Exception;
    ArrayList<ReturnBookDto> getAll()throws Exception;
    ReturnBookDto get(String id)throws Exception;
}
