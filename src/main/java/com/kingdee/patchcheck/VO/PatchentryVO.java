package com.kingdee.patchcheck.VO;

import java.sql.Date;
import java.util.PrimitiveIterator;

/**
 * description: PatchentryVO <br>
 * date: 2020\1\19 0019 14:48 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁组件
 */
public class PatchentryVO {
    //编号
    private Integer id;
    //名称
    private String name;
    //说明
    private String remarks;
    //类型
    private String type;
    //创建时间
    private Date createtime;
    //创建人
    private String createuser;
    //修改时间
    private Date updatetime;
    //修改人
    private String updateuser;
    //是否校验成功
    private String ischeck;



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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }
}
