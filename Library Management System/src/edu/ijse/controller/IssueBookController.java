package edu.ijse.controller;

import edu.ijse.dto.IssueBookDto;
import edu.ijse.service.ServiceFactory;
import edu.ijse.service.custom.IssueBookService;

import java.util.ArrayList;

public class IssueBookController {
    private IssueBookService issueBookService = (IssueBookService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ISSUE_BOOKS);

    public String save(IssueBookDto issueBookDto) throws Exception {
        return issueBookService.save(issueBookDto);
    }

    public String update(IssueBookDto issueBookDto) throws Exception {
        return issueBookService.update(issueBookDto);
    }

    public String delete(String issueId) throws Exception {
        return issueBookService.delete(issueId);
    }

    public ArrayList<IssueBookDto> getAll() throws Exception {
        return issueBookService.getAll();
    }

    public IssueBookDto getIssueBook(String issueId) throws Exception {
        return issueBookService.get(issueId);
    }
}
