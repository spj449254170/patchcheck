package com.kingdee.patchcheck.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * description: patchEntry <br>
 * date: 2020\1\7 0007 18:44 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁详细模型
 */
@Entity
public class patchEntry {
    @Id
    @GeneratedValue
    private String id;
    //补丁id
    private String patchid;
    //补丁类型
    private String type;
    //是否通过校验
    private Boolean ischeck;
    //名称
    private String name;
    //说明
    private String remarks;
    @CreationTimestamp
    //创建时间
    private Date createtime;

    public patchEntry(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
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
}
