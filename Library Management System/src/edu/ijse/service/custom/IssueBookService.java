package edu.ijse.service.custom;

import edu.ijse.dto.IssueBookDto;
import edu.ijse.service.SuperService;

import java.util.ArrayList;

public interface IssueBookService extends SuperService {
    String save(IssueBookDto issueBookDto)throws Exception;
    String update(IssueBookDto issueBookDto)throws Exception;
    String delete(String issueId)throws Exception;
    IssueBookDto get(String issueId)throws Exception;
    ArrayList<IssueBookDto> getAll()throws Exception;
}
