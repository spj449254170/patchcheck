package com.kingdee.patchcheck.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.VO.PatchentryVO;
import com.kingdee.patchcheck.model.*;
import com.kingdee.patchcheck.repository.*;
import com.kingdee.patchcheck.service.IpatchEntryService;
import com.kingdee.patchcheck.service.IpatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * description: userServiceimpl <br>
 * date: 2020\1\8 0008 13:46 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁处理实现类
 */
@Service
@Transactional
public class patchEntryServiceimpl implements IpatchEntryService {


    @Autowired
    private userRepository userRepository;

    @Autowired
    private patchEntryRepository patchEntryRepository;
    @Autowired
    private patchtypeRepository patchtypeRepository;
    @Autowired
    private patchLogRepository patchLogRepository;
    @Autowired
    private patchRepository patchRepository;
    @Autowired
    private itemRepository itemRepository;

    @Override
    public Page<PatchentryVO> getpatchentry(Integer page, Integer size, Integer patchid) {
        if (null == page) {
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        List<PatchEntry> patchEntries = patchEntryRepository.findByPatchidAndIsclose(patchid, pageable, false);
        List<PatchentryVO> patchentryVOS = new ArrayList<PatchentryVO>();
        for (PatchEntry patchEntry : patchEntries) {
            PatchentryVO patchentryVOh = new PatchentryVO();
            patchentryVOh.setId(patchEntry.getId());
            patchentryVOh.setName(patchEntry.getName());
            patchentryVOh.setRemarks(patchEntry.getRemarks());
            patchentryVOh.setCreatetime(patchEntry.getCreatetime());
            patchentryVOh.setUpdatetime(patchEntry.getUpdatetime());
            patchentryVOh.setIscheck(patchEntry.getIscheck() ? "通过校验" : "未通过校验");
            Optional<Patchtype> patchtype = patchtypeRepository.findById(patchEntry.getType());

            patchentryVOh.setType(patchtype.get().getName());
            Optional<User> optionalUser = userRepository.findById(patchEntry.getCreateuserid());
            patchentryVOh.setCreateuser(optionalUser.get().getName());
            if (null != patchEntry.getUpdateuserid()) {
                Optional<User> optionalUser1 = userRepository.findById(patchEntry.getUpdateuserid());
                patchentryVOh.setUpdateuser(optionalUser1.get().getName());
            }
            patchentryVOS.add(patchentryVOh);
        }

        return new PageImpl<PatchentryVO>(patchentryVOS, pageable, patchentryVOS.size() / 10 + 1);

    }

    @Override
    public Boolean addpatchEntry(PatchEntry patchEntry, User user, Integer patchid) {
        patchEntry.setCreateuserid(user.getId());
        patchEntry.setPatchid(patchid);
        PatchEntry patchEntry1 = patchEntryRepository.save(patchEntry);
        if (null != patchEntry1) {
            //增加日志
            patchLog patchLog = new patchLog();
            patchLog.setPatchid(patchid);
            patchLog.setUserid(user.getId());
            patchLog.setUpdateinfo(user.getName() + "生成里一个补丁组件，补丁组件为" + patchEntry1.getName());
            patchLogRepository.save(patchLog);
            return true;
        }
        return false;


    }

    @Override
    public Boolean deletepatchentry(Integer id, User user) {
        Optional<PatchEntry> optionalPatchEntry = patchEntryRepository.findById(id);
        optionalPatchEntry.get().setIsclose(true);
        optionalPatchEntry.get().setUpdatetime(new Date(new java.util.Date().getTime()));
        optionalPatchEntry.get().setUpdateuserid(user.getId());
        patchEntryRepository.save(optionalPatchEntry.get());
        return true;
    }

    @Override
    public Item getpatch(Integer patchid) {

        Optional<Patch> patch = patchRepository.findById(patchid);
        Optional<Item> item = itemRepository.findById(patch.get().getItemid());

        return item.get();
    }

    /*@Override
    public Optional<Item> getitembyid(Integer id) {
        Optional<Item> item1 = itemRepository.findById(id);
        return item1;
    }

    @Override
    public Page<Item> getuserbyname(String name) {
        PageRequest pageable = PageRequest.of(0, 1);
        Page<Item> items = itemRepository.findByNameLike(name, pageable);
        return items;
    }

    @Override
    public Page<User> getitemuserlist(Integer id) {
        List<Object[]> objects=itemRepository.findByIdContaining(id);
        List<User> userList = new ArrayList<User>();
        for(Object[] a:objects){
            User user = new User();
            user.setName((String) a[0]);
            user.setDepartment(((Integer) a[1]));
            user.setId((Integer) a[2]);
            userList.add(user);
        }
        PageRequest pageable = PageRequest.of(0, 10);
        return new PageImpl<User>(userList, pageable,objects.size()/10+1);
    }

    @Override
    public Page<User> getusernoinitem(Integer id) {
        List<Object[]> objects = itemRepository.findNOByIdContaining(id);
        List<User> userList = new ArrayList<User>();
        for(Object[] o :objects){
            User user = new User();
            user.setId((Integer) o[0]);
            user.setName((String) o[1]);
            user.setDepartment((Integer) o[2]);
            userList.add(user);
        }
        PageRequest pageable = PageRequest.of(0, 10);
        return new PageImpl<User>(userList, pageable,objects.size()/10+1);
    }

    @Override
    public Boolean adduser(String user, Integer itemid) {
        List<ItemandUser> itemandUserList = new ArrayList<ItemandUser>();
        JSONArray objects = JSON.parseArray(user);
        System.out.println(objects);
        for (int i = 0;i<objects.size();i++){
            JSONObject jsonObject = (JSONObject)objects.get(i);
            ItemandUser itemandUser = new ItemandUser();
            itemandUser.setItemid(itemid);
            itemandUser.setUserid(jsonObject.getIntValue("id"));
            ItemandUser itemandUser1 =itemanduserRepository.save(itemandUser);
            itemandUserList.add(itemandUser1);
        }

        if(itemandUserList.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateitem(Item item) {
        Item updateitem = new Item();
        updateitem.setId(item.getId());
        updateitem.setName(item.getName());
        updateitem.setRemarks(item.getRemarks());
        Optional<Item> item1 = itemRepository.findById(item.getId());
        updateitem.setCreatetime(item1.get().getCreatetime());
        updateitem.setClosetype(item1.get().getClosetype());
        updateitem.setEndtime(item1.get().getEndtime());
        updateitem.setUserid(item1.get().getUserid());
        Item item2 = itemRepository.save(updateitem);
        if (null == item2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean deleteuser(Integer userid, Integer itemid) {
        itemanduserRepository.deleteByUserid(userid,itemid);
        return true;
    }

    @Override
    public Boolean closeitem(Integer id) {
        Optional<Item> optional =   itemRepository.findById(id);
        Item item = optional.get();
        item.setClosetype(!item.getClosetype());
        itemRepository.save(item);
        return true;
    }*/


}
