package com.kingdee.patchcheck.service;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * description: IuserService <br>
 * date: 2020\1\8 0008 13:47 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 普通用户逻辑层
 */
public interface InormalitemService {

    public Page<Item> getitem(Integer page, Integer size,User user);
    public Boolean additem(Item item, User user);
    public Boolean deleteitem(Integer id);
    public Optional<Item> getitembyid(Integer id);
    public Page<Item> getuserbyname(String name);
    public Page<User> getitemuserlist(Integer id);
    public Page<User> getusernoinitem(Integer id);
    public Boolean adduser(String user, Integer itemid);
    public Boolean updateitem(Item item);
    public Boolean deleteuser(Integer userid, Integer itemid);
    public Boolean closeitem(Integer id);
}
