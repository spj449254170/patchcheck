package com.kingdee.patchcheck.VO;

/**
 * description: patchAndUser <br>
 * date: 2020\1\17 0017 14:40 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁对象
 */
public class PatchVO {

    private Integer id;
    private String name;
    private String remarks;
    private String username;
    private String ishaszip;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIshaszip() {
        return ishaszip;
    }

    public void setIshaszip(String ishaszip) {
        this.ishaszip = ishaszip;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
