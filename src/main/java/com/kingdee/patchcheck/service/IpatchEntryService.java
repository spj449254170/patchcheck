package com.kingdee.patchcheck.service;

import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.VO.PatchentryVO;
import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Patch;
import com.kingdee.patchcheck.model.PatchEntry;
import com.kingdee.patchcheck.model.User;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * description: IuserService <br>
 * date: 2020\1\8 0008 13:47 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁逻辑层
 */
public interface IpatchEntryService {

    public Page<PatchentryVO> getpatchentry(Integer page, Integer size, Integer patchid);
    public Boolean addpatchEntry(PatchEntry patch, User user, Integer patchid);
    public Boolean deletepatchentry(Integer id,User user);
    Item getpatch(Integer patchentryid);
    /*public Optional<Item> getitembyid(Integer id);
    public Page<Item> getuserbyname(String name);
    public Page<User> getitemuserlist(Integer id);
    public Page<User> getusernoinitem(Integer id);
    public Boolean adduser(String user, Integer itemid);
    public Boolean updateitem(Item item);
    public Boolean deleteuser(Integer userid, Integer itemid);
    public Boolean closeitem(Integer id);*/
}
