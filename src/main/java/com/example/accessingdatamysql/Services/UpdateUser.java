package com.example.accessingdatamysql.Services;

public class UpdateUser {

    private String name;
    private String email;
    private Boolean member;
    private Integer conferance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer members;

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }

    public Integer getConferance() {
        return conferance;
    }

    public void setConferance(Integer conferance) {
        this.conferance = conferance;
    }
}
