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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * description: itemServiceimpl <br>
 * date: 2020\1\8 0008 13:46 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目实现类
 */
@Service
@Transactional
public class itemServiceimpl implements IitemService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private itemRepository itemRepository;
    @Autowired
    private itemanduserRepository itemanduserRepository;
    @Autowired
    private departmentRepository departmentRepository;


    @Override
    public Page<Item> getitem(Integer page, Integer size) {
        logger.info("分页获取项目  入参page:{},size:{}",page,size);
        if (null == page) {
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        Page<Item> all = itemRepository.findAll(pageable);
        logger.info("分页获取项目，出参 all:{}",all.toString());
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
        logger.info("删除项目实现类，入参 id:{}",id);
        itemRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Item> getitembyid(Integer id) {
        logger.info("通过id获取项目实现类，入参id:{}",id);
        Optional<Item> item1 = itemRepository.findById(id);
        logger.info("通过id获取项目实现类，出参item1:{}",item1.toString());
        return item1;
    }

    @Override
    public Page<Item> getitembyname(String name) {
        logger.info("通过项目名查找项目实现类，入参name:{}",name);
        PageRequest pageable = PageRequest.of(0, 1);
        Page<Item> items = itemRepository.findByNameLike(name, pageable);
        logger.info("通过项目名查找项目实现类，出参items:{}",items.toString());
        return items;
    }

    @Override
    public Page<User> getitemuserlist(Integer id) {
        logger.info("获取项目人员列表实现类，入参id:{}",id);
        List<Object[]> objects=itemRepository.findByIdContaining(id);
        List<User> userList = new ArrayList<User>();
        for(Object[] a:objects){
            User user = new User();
            user.setName((String) a[0]);
            user.setDepartment(((Integer) a[1]));
            user.setId((Integer) a[2]);
            userList.add(user);
        }
        PageRequest pageable = PageRequest.of(userList.size()/10+1, 10);
        Page<User> users = new PageImpl<User>(userList, pageable,objects.size()/10+1);
        logger.info("获取项目人员列表实现类，出参users:{}",users.toString());
        return users;
    }

    @Override
    public Page<User> getusernoinitem(Integer id) {
        logger.info("获取不在项目人员列表实现类 入参 id:{}",id);
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
        PageImpl<User> user =new PageImpl<User>(userList, pageable,objects.size()/10+1);
        logger.info("获取不在项目人员列表实现类 出参 user:{}",user);
        return user;
    }

    @Override
    public Boolean adduser(String user, Integer itemid) {
        logger.info("项目添加用户实现类，入参user:{},itemid:{}",user,itemid);
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
        logger.info("项目修改实现类,入参item:{}",item.toString());
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
            logger.info("项目修改实现类,出参result:{}",false);
            return false;
        } else {
            logger.info("项目修改实现类,出参result:{}",true);
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
        logger.info("关闭项目实现类，入参id:{}",id);
        Optional<Item> optional =   itemRepository.findById(id);
        Item item = optional.get();
        item.setClosetype(!item.getClosetype());
        itemRepository.save(item);
        return true;
    }


}
