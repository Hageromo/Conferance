package com.example.accessingdatamysql.Path;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="path")
public class Path{

    @Id
    private Integer id;
    private Integer limit1;
    private Integer limit2;
    private Integer limit3;


    public Integer getLimit1() {
        return limit1;
    }

    public void setLimit1(Integer limit1) {
        this.limit1 = limit1;
    }

    public Integer getLimit2() {
        return limit2;
    }

    public void setLimit2(Integer limit2) {
        this.limit2 = limit2;
    }

    public Integer getLimit3() {
        return limit3;
    }

    public void setLimit3(Integer limit3) {
        this.limit3 = limit3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
