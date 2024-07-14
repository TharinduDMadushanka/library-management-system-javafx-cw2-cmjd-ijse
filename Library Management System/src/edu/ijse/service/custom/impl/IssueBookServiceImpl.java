package edu.ijse.service.custom.impl;

import edu.ijse.dao.DaoFactory;
import edu.ijse.dao.custom.IssueBookDao;
import edu.ijse.dto.IssueBookDto;
import edu.ijse.entity.IssueBookEntity;
import edu.ijse.service.custom.IssueBookService;

import java.util.ArrayList;

public class IssueBookServiceImpl implements IssueBookService {

    private IssueBookDao issueBookDao = (IssueBookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.ISSUE_BOOKS);

    @Override
    public String save(IssueBookDto issueBookDto) throws Exception {
        IssueBookEntity issueBookEntity = getIssueBookEntity(issueBookDto);
        return issueBookDao.create(issueBookEntity) ? "Success" : "Fail";
    }

    @Override
    public String update(IssueBookDto issueBookDto) throws Exception {
        IssueBookEntity issueBookEntity = getIssueBookEntity(issueBookDto);
        return issueBookDao.update(issueBookEntity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String issueId) throws Exception {
        return issueBookDao.delete(issueId) ? "Success" : "Fail";
    }

    @Override
    public IssueBookDto get(String issueId) throws Exception {
        IssueBookEntity issueBookEntity = issueBookDao.get(issueId);
        if (issueBookEntity != null) {
            return getIssueBookDto(issueBookEntity);
        }
        return null;
    }

    @Override
    public ArrayList<IssueBookDto> getAll() throws Exception {
        ArrayList<IssueBookEntity> entities = issueBookDao.getAll();

        if (entities != null && !entities.isEmpty()) {
            ArrayList<IssueBookDto> issueBookDtos = new ArrayList<>();
            for (IssueBookEntity entity : entities) {
                issueBookDtos.add(getIssueBookDto(entity));
            }
            return issueBookDtos;
        }
        return null;
    }

    private IssueBookEntity getIssueBookEntity(IssueBookDto issueBookDto) throws Exception {
        return new IssueBookEntity(
                issueBookDto.getIssueId(),
                issueBookDto.getBookId(),
                issueBookDto.getBookDetails(),
                issueBookDto.getMemberId(),
                issueBookDto.getMemberDetails(),
                issueBookDto.getIssueDate(),
                issueBookDto.getDueDate()
        );
    }

    private IssueBookDto getIssueBookDto(IssueBookEntity entity) throws Exception {
        return new IssueBookDto(
                entity.getIssueId(),
                entity.getBookId(),
                entity.getBookDetails(),
                entity.getMemberId(),
                entity.getMemberDetails(),
                entity.getIssueDate(),
                entity.getDueDate()
        );
    }
}
