package com.kingdee.patchcheck.model;

import io.swagger.models.auth.In;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * description: patch <br>
 * date: 2020\1\7 0007 18:39 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁模型
 */
@Entity
public class Patch {
    @Id
    @GeneratedValue
    //编号
    private Integer id;
    //提交人
    private Integer commitman;
    @CreationTimestamp
    //提交时间
    private Date createtime;
    //是否通过校验
    private Boolean ischeck;
    //补丁名字
    private String name;
    //补丁说明
    private String remarks;
    //补丁路径
    private Integer realUrl;
    //所属项目
    private Integer itemid;


    public Integer getItemid() {
        return itemid;
    }



    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Patch(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommitman() {
        return commitman;
    }

    public void setCommitman(Integer commitman) {
        this.commitman = commitman;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public Integer getRealUrl() {
        return realUrl;
    }

    public void setRealUrl(Integer realUrl) {
        this.realUrl = realUrl;
    }
}
