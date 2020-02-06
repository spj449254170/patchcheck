package com.kingdee.patchcheck.service;

import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Patch;
import com.kingdee.patchcheck.model.User;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * description: IuserService <br>
 * date: 2020\1\8 0008 13:47 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁逻辑层
 */
public interface IpatchService {

    public Page<PatchVO> getpatch(Integer page, Integer size, Integer itemid);
    public Boolean addpatch(Patch patch, User user, Integer itemid);
    public Boolean createpatch(Integer patchid,User user);
    String downloadpatch(User user, Integer patchid, HttpServletResponse response) throws IOException;
    /*public Boolean deleteitem(Integer id);
    public Optional<Item> getitembyid(Integer id);
    public Page<Item> getuserbyname(String name);
    public Page<User> getitemuserlist(Integer id);
    public Page<User> getusernoinitem(Integer id);
    public Boolean adduser(String user, Integer itemid);
    public Boolean updateitem(Item item);
    public Boolean deleteuser(Integer userid, Integer itemid);
    public Boolean closeitem(Integer id);*/
}
