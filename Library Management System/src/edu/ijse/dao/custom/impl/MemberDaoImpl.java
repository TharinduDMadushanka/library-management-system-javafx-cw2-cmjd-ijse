package edu.ijse.dao.custom.impl;

import edu.ijse.dao.CrudUtil;
import edu.ijse.dao.custom.MemberDao;
import edu.ijse.entity.MemberEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDaoImpl implements MemberDao {
    @Override
    public boolean create(MemberEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO members VALUES(?,?,?,?,?,?,?,?) ",t.getMemberId(),t.getMemberName(),t.getAddress(),
                t.getMobile(),t.getEmail(),t.getAge(),t.getDob(),t.getGender());
    }

    @Override
    public boolean update(MemberEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE members SET memberName=?,address=?,mobileNumber=?,email=?,age=?,dob=?,gender=? WHERE memberId=?",
                t.getMemberName(),t.getAddress(), t.getMobile(),t.getEmail(),t.getAge(),t.getDob(),t.getGender(),t.getMemberId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM members WHERE memberId=?",id);
    }

    @Override
    public MemberEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM members WHERE memberId=?",id);
        if(rst.next()){
            MemberEntity memberEntity = new MemberEntity(
                    rst.getString("memberId"),
                    rst.getString("memberName"),
                    rst.getString("address"),
                    rst.getString("mobileNumber"),
                    rst.getString("email"),
                    rst.getInt("age"),
                    rst.getDate("dob").toLocalDate(),
                    rst.getString("gender")
            );
            return memberEntity;
        }
        return null;
    }

    @Override
    public ArrayList<MemberEntity> getAll() throws Exception {
        ArrayList<MemberEntity> memberEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM members");

        while(rst.next()){
//            System.out.println(rst.getString("mobileNumber"));

            MemberEntity memberEntity = new MemberEntity(
                    rst.getString("memberId"),
                    rst.getString("memberName"),
                    rst.getString("address"),
                    rst.getString("mobileNumber"),
                    rst.getString("email"),
                    rst.getInt("age"),
                    rst.getDate("dob").toLocalDate(),
                    rst.getString("gender")
            );
            memberEntities.add(memberEntity);
        }
        return memberEntities;
    }
}