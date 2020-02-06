package com.kingdee.patchcheck.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * description: ItemandUser <br>
 * date: 2020\1\16 0016 9:01 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目与用户关联表
 */
@Entity
public class ItemandUser {
    @Id
    @GeneratedValue
    private Integer id;
    //用户id
    private Integer userid;
    //项目id
    private Integer itemid;

    public ItemandUser(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }
}
