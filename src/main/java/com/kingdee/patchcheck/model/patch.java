package com.kingdee.patchcheck.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
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
public class patch {
    @Id
    //编号
    private String id;
    //提交人
    private String commitman;
    @CreationTimestamp
    //提交时间
    private Date createtime;
    //是否通过校验
    private Boolean ischeck;
    //补丁名字
    private String name;
    //补丁说明
    private String remarks;

    public patch(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommitman() {
        return commitman;
    }

    public void setCommitman(String commitman) {
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
}
