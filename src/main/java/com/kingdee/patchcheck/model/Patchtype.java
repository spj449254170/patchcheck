package com.kingdee.patchcheck.model;

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
public class Patchtype {
    @Id
    @GeneratedValue
    //编号
    private Integer id;
    //补丁类型
    private String name;
    //补丁说明
    private String remarks;
    //打包路径
    private String Url;
    //是否生效
    private Boolean istrue;
    //校验规则
    private String ruler;
    public Integer getId() {
        return id;
    }

    public String getRuler() {
        return ruler;
    }

    public void setRuler(String ruler) {
        this.ruler = ruler;
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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Boolean getIstrue() {
        return istrue;
    }

    public void setIstrue(Boolean istrue) {
        this.istrue = istrue;
    }
}
