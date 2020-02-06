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
public class Item {
    @Id
    @GeneratedValue
    private Integer id;
    //项目编号
    private String name;
    //项目描述
    private String remarks;
    @CreationTimestamp
    //创建时间，也是项目开始时间
    private Date createtime;
    //项目结束时间
    private Date endtime;
    //是否关闭
    private Boolean closetype;
    //创建人id
    private Integer userid;

    public Item(){

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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
