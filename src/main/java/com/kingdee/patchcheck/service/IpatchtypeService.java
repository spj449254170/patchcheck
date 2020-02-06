package com.kingdee.patchcheck.service;

import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Patch;
import com.kingdee.patchcheck.model.Patchtype;
import com.kingdee.patchcheck.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * description: IuserService <br>
 * date: 2020\1\8 0008 13:47 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁类型逻辑层
 */
public interface IpatchtypeService {

    //public Page<PatchVO> getpatch(Integer page, Integer size, Integer itemid);
    public Boolean addpatchtype(Patchtype patch);
    List<Patchtype> getpatchlist();
    public Boolean deletepatchtype(Integer id);
    //public Optional<Item> getitembyid(Integer id);
    //public Page<Item> getuserbyname(String name);
    //public Page<User> getitemuserlist(Integer id);
    //public Page<User> getusernoinitem(Integer id);
    //public Boolean adduser(String user, Integer itemid);
    public Boolean updatepatchtype(Patchtype patchtype);
    //public Boolean deleteuser(Integer userid, Integer itemid);
    //public Boolean closeitem(Integer id);
}
