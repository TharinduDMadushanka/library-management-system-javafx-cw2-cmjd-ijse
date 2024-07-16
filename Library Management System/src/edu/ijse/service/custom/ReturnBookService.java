package edu.ijse.service.custom;

import edu.ijse.dto.ReturnBookDto;
import edu.ijse.service.SuperService;

import java.util.ArrayList;

public interface ReturnBookService extends SuperService {
    String save(ReturnBookDto returnBookDto)throws Exception;
    String update(ReturnBookDto returnBookDto)throws Exception;
    String delete(int id)throws Exception;
    ReturnBookDto get(int id)throws Exception;
    ArrayList<ReturnBookDto> getAll()throws Exception;
}
