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
    private Integer id;
    //补丁id
    private Integer patchid;
    @CreationTimestamp
    private Date createtime;
    //用户id
    private Integer userid;
    //操作说明
    private String updateinfo;

    public patchLog(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatchid() {
        return patchid;
    }

    public void setPatchid(Integer patchid) {
        this.patchid = patchid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUpdateinfo() {
        return updateinfo;
    }

    public void setUpdateinfo(String updateinfo) {
        this.updateinfo = updateinfo;
    }
}
