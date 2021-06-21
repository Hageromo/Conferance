package com.example.accessingdatamysql.Conferance;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Conferance {


    @Id
    private Integer id;
    private String hour; //godzina rozpoczecia
    private String end; //godzina zakonczenia
    private Integer members; //ilosc wolnych miejsc
    private String date;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }
}
