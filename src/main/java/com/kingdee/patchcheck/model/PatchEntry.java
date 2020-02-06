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
public class PatchEntry {
    @Id
    @GeneratedValue
    private Integer id;
    //补丁id
    private Integer patchid;
    //补丁类型
    private Integer type;
    //是否通过校验
    private Boolean ischeck;
    //名称
    private String name;
    //说明
    private String remarks;
    @CreationTimestamp
    //创建时间
    private Date createtime;
    //创建人
    private Integer createuserid;
    //修改时间
    private Date updatetime;
    //修改人
    private Integer updateuserid;
    //是否打入补丁中
    private Boolean istoin;
    //文件信息
    private Integer fileid;
    //是否关闭
    private Boolean isclose=false;
    public PatchEntry(){

    }

    public Boolean getIsclose() {
        return isclose;
    }

    public void setIsclose(Boolean isclose) {
        this.isclose = isclose;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Integer getPatchid() {
        return patchid;
    }

    public void setPatchid(Integer patchid) {
        this.patchid = patchid;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(Integer updateuserid) {
        this.updateuserid = updateuserid;
    }

    public Boolean getIstoin() {
        return istoin;
    }

    public void setIstoin(Boolean istoin) {
        this.istoin = istoin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
