package com.kingdee.patchcheck.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.ItemandUser;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.repository.departmentRepository;
import com.kingdee.patchcheck.repository.itemRepository;
import com.kingdee.patchcheck.repository.itemanduserRepository;
import com.kingdee.patchcheck.repository.userRepository;
import com.kingdee.patchcheck.service.IitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * description: userServiceimpl <br>
 * date: 2020\1\8 0008 13:46 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * TODO
 */
@Service
@Transactional
public class itemServiceimpl implements IitemService {

    @Autowired
    private itemRepository itemRepository;
    @Autowired
    private itemanduserRepository itemanduserRepository;
    @Autowired
    private departmentRepository departmentRepository;


    @Override
    public Page<Item> getitem(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        Page<Item> all = itemRepository.findAll(pageable);
        return all;
    }

    @Override
    public Boolean additem(Item item,User user) {

        item.setClosetype(false);
        item.setUserid(user.getId());
        Item save = itemRepository.save(item);
        if (null == save) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteitem(Integer id) {
        itemRepository.deleteById(id);
        return true;
    }

    @Override
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
    }


}
