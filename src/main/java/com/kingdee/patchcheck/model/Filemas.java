package com.kingdee.patchcheck.model;

import io.swagger.models.auth.In;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * description: File <br>
 * date: 2020\1\19 0019 10:26 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 文件模型
 */
@Entity
public class Filemas {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String type;
    private String url;
    @CreationTimestamp
    private Date createtime;

    public Filemas(){}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
