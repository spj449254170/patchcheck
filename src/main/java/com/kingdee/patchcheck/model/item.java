package com.kingdee.patchcheck.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * description: item <br>
 * date: 2020\1\7 0007 17:29 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目模型
 */
@Entity
public class item {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String remarks;
    @CreationTimestamp
    private Date createtime;
    private Date endtime;
    private Boolean closetype;
    private String userid;

    public item(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Boolean getClosetype() {
        return closetype;
    }

    public void setClosetype(Boolean closetype) {
        this.closetype = closetype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
