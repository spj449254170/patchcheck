package com.kingdee.patchcheck.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * description: Power <br>
 * date: 2020\1\14 0014 16:09 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 部门表
 */
@Entity
public class Department {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    public Department(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
