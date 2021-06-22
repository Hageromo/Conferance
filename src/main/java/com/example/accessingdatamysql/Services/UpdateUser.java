package com.example.accessingdatamysql.Services;

public class UpdateUser {

    private String name;
    private String email;
    private Integer conferance;
    private Integer path;

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }

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

    public Integer getConferance() {
        return conferance;
    }

    public void setConferance(Integer conferance) {
        this.conferance = conferance;
    }
}