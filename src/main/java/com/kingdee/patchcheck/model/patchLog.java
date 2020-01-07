package com.kingdee.patchcheck.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * description: patchLog <br>
 * date: 2020\1\7 0007 18:48 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁日志模型
 */
@Entity
public class patchLog {
    @Id
    @GeneratedValue
    private String id;
    private String patchid;
    @CreationTimestamp
    private Date createtime;
    private String userid;
    private String updateinfo;

    public patchLog(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatchid() {
        return patchid;
    }

    public void setPatchid(String patchid) {
        this.patchid = patchid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUpdateinfo() {
        return updateinfo;
    }

    public void setUpdateinfo(String updateinfo) {
        this.updateinfo = updateinfo;
    }
}
