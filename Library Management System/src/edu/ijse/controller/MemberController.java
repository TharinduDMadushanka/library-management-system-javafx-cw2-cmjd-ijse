package edu.ijse.controller;

import edu.ijse.dto.MemberDto;
import edu.ijse.service.ServiceFactory;
import edu.ijse.service.custom.MemberService;

import java.util.ArrayList;

public class MemberController {
    private MemberService memberService = (MemberService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MEMBERS);

    public String save(MemberDto memberDto)throws Exception{
        return memberService.save(memberDto);
    }

    public String update(MemberDto memberDto)throws Exception{
        return memberService.update(memberDto);
    }

    public String delete(String memberId)throws Exception{
        return memberService.delete(memberId);
    }

    public ArrayList<MemberDto> getAll()throws Exception{
        return memberService.getAll();
    }

    public MemberDto getMember(String memberId)throws Exception{
        return memberService.get(memberId);
    }
}
