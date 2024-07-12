package edu.ijse.dto;

import java.time.LocalDate;

public class MemberDto {

    private String memberId;
    private String memberName;
    private String address;
    private String mobile;
    private String email;
    private int age;
    private LocalDate dob;
    private String gender;

    public MemberDto() {
    }

    public MemberDto(String memberId, String memberName, String address, String mobile, String email, int age, LocalDate dob, String gender) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                '}';
    }
}
