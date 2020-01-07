package com.kingdee.patchcheck.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity

public class user {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "COMMENT'id'")
    private String id;
    //名称
    @Column(columnDefinition = "COMMENT'用户名也是登录账号'")
    private String name;
    //密码
    @Column(columnDefinition = "COMMENT'密码'")
    private String password;
    //权限
    @Column(columnDefinition = "COMMENT'权限'")
    private Integer power;
    //部门
    @Column(columnDefinition = "COMMENT'所属部门'")
    private String department;
    //创建时间
    @Column(columnDefinition = "COMMENT'创建时间'")
    @CreationTimestamp
    private Date createTime;
    //手机号
    @Column(columnDefinition = "COMMENT'手机号'")
    private Integer phone;

    public user(){

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
