package com.kingdee.patchcheck.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.model.*;
import com.kingdee.patchcheck.repository.*;
import com.kingdee.patchcheck.service.IpatchService;
import com.kingdee.patchcheck.service.IpatchtypeService;
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
 * 补丁处理实现类
 */
@Service
@Transactional
public class patchtypeServiceimpl implements IpatchtypeService {

   /* @Autowired
    private itemRepository itemRepository;
    @Autowired
    private itemanduserRepository itemanduserRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private patchRepository patchRepository;*/
    @Autowired
    private patchtypeRepository patchtypeRepository;

   /* @Override
    public Page<PatchVO> getpatch(Integer page, Integer size, Integer itemid) {
        if (null == page) {
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        List<Patch> patchList = patchRepository.findByItemid(itemid,pageable);
        List<PatchVO> patchVOList = new ArrayList<PatchVO>();
        for(Patch patch:patchList){
            PatchVO patchVO = new PatchVO();
            patchVO.setId(patch.getId());
            patchVO.setName(patch.getName());
            patchVO.setRemarks(patch.getRemarks());
            Optional<User> optionalUser =userRepository.findById(patch.getCommitman());
            patchVO.setUsername(optionalUser.get().getName());
            patchVOList.add(patchVO);
        }

        return new PageImpl<PatchVO>(patchVOList, pageable,patchList.size()/10+1);

    }*/

    @Override
    public Boolean addpatchtype(Patchtype patch) {
        patch.setIstrue(true);
        Patchtype patchtype=patchtypeRepository.save(patch);
        if(null==patchtype){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public List<Patchtype> getpatchlist() {

        return patchtypeRepository.findAll();
    }
    @Override
    public Boolean deletepatchtype(Integer id) {
        patchtypeRepository.deleteById(id);
        return true;
    }
    @Override
    public Boolean updatepatchtype(Patchtype patchtype) {
        Patchtype patchtype1 = new Patchtype();
        patchtype1.setId(patchtype.getId());
        patchtype1.setName(patchtype.getName());
        patchtype1.setRemarks(patchtype.getRemarks());
        patchtype1.setRuler(patchtype.getRuler());
        patchtype1.setIstrue(patchtype.getIstrue());

        Patchtype patchtype2 = patchtypeRepository.save(patchtype1);
        if (null == patchtype2) {
            return false;
        } else {
            return true;
        }
    }
   /*

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
    }*/


}
