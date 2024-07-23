package edu.ijse.service.custom.impl;

import edu.ijse.dao.DaoFactory;
import edu.ijse.dao.custom.MemberDao;
import edu.ijse.dto.MemberDto;
import edu.ijse.entity.MemberEntity;
import edu.ijse.service.custom.MemberService;

import java.util.ArrayList;

public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao = (MemberDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEMBERS);

    @Override
    public String save(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String memberId) throws Exception {
        return memberDao.delete(memberId) ? "Success" : "Fail";
    }

    @Override
    public MemberDto get(String memberId) throws Exception {
        MemberEntity entity = memberDao.get(memberId);
        if (entity != null) {
            return getMemberDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<MemberDto> getAll() throws Exception {
        ArrayList<MemberEntity> entities = memberDao.getAll();

        if (entities != null && !entities.isEmpty()) {
            ArrayList<MemberDto> memberDtos = new ArrayList<>();
            for (MemberEntity entity : entities) {
//                System.out.println(entity.getMobile());
                memberDtos.add(getMemberDto(entity));
            }
            return memberDtos;
        }

        return null;
    }

    private MemberEntity getMemberEntity(MemberDto memberDto) throws Exception {
        return new MemberEntity(
                memberDto.getMemberId(),
                memberDto.getMemberName(),
                memberDto.getAddress(),
                memberDto.getMobile(),
                memberDto.getEmail(),
                memberDto.getAge(),
                memberDto.getDob(),
                memberDto.getGender()
        );
    }

    private MemberDto getMemberDto(MemberEntity entity) throws Exception {
        return new MemberDto(
                entity.getMemberId(),
                entity.getMemberName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getEmail(),
                entity.getAge(),
                entity.getDob(),
                entity.getGender()
        );
    }
}