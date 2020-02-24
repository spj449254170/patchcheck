package com.kingdee.patchcheck.service.impl;

import com.google.common.collect.Lists;
import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.model.*;
import com.kingdee.patchcheck.repository.*;
import com.kingdee.patchcheck.service.IpatchService;
import com.kingdee.patchcheck.utils.FTPUtil;
import com.kingdee.patchcheck.utils.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;

/**
 * description: userServiceimpl <br>
 * date: 2020\1\8 0008 13:46 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁处理实现类
 */
@Service
@Transactional
public class patchServiceimpl implements IpatchService {

    /* @Autowired
     private itemRepository itemRepository;
     @Autowired
     private itemanduserRepository itemanduserRepository;*/
    @Autowired
    private userRepository userRepository;
    @Autowired
    private patchRepository patchRepository;
    @Autowired
    private patchEntryRepository patchEntryRepository;
    @Autowired
    private filemsgRepository filemsgRepository;
    @Autowired
    private patchtypeRepository patchtypeRepository;
    @Autowired
    private patchLogRepository patchLogRepository;


    @Override
    public Page<PatchVO> getpatch(Integer page, Integer size, Integer itemid) {
        if (null == page) {
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        List<Patch> patchList = patchRepository.findByItemid(itemid, pageable);
        List<PatchVO> patchVOList = new ArrayList<PatchVO>();
        for (Patch patch : patchList) {
            PatchVO patchVO = new PatchVO();
            patchVO.setId(patch.getId());
            patchVO.setName(patch.getName());
            patchVO.setRemarks(patch.getRemarks());
            patchVO.setIshaszip(StringUtils.isEmpty(patch.getRealUrl()) ? "没有生成压缩包" : "已有压缩包");
            if(!StringUtils.isEmpty(patch.getRealUrl())){
                Optional<Filemas> filemas = filemsgRepository.findById(patch.getRealUrl());
                patchVO.setUrl(filemas.get().getUrl());
            }
            Optional<User> optionalUser = userRepository.findById(patch.getCommitman());
            patchVO.setUsername(optionalUser.get().getName());
            patchVOList.add(patchVO);
        }

        return new PageImpl<PatchVO>(patchVOList, pageable, patchList.size() / 10 + 1);

    }

    @Override
    public Boolean addpatch(Patch patch, User user, Integer itemid) {
        patch.setCommitman(user.getId());
        patch.setItemid(itemid);
        Patch patch1 = patchRepository.save(patch);
        if (null == patch1) {
            return false;
        } else {
            //增加日志
            patchLog patchLog = new patchLog();
            patchLog.setPatchid(patch1.getId());
            patchLog.setUserid(user.getId());
            patchLog.setUpdateinfo(user.getName() + "新增一条补丁，补丁名称为" + patch1.getName());
            patchLogRepository.save(patchLog);
            return true;
        }

    }

    @Override
    public Boolean createpatch(Integer patchid, User user) {
        /*1.获取所有的这个补丁校验通过，没有删除的组件
        2.将这些组件放到一个文件夹中
        3.在这个文件夹中添加部署说明
        4.添加补丁说明文件
        5.压缩补丁
        6.存起来
        7.更新数据库
         */
        Optional<Patch> patch = patchRepository.findById(patchid);
        List<PatchEntry> patchEntryList = patchEntryRepository.findByPatchidAndIscloseAndIscheck(patchid, false, true);
        File Files = new File("F:/patchcheck/" + UUID.randomUUID().toString());
        Map<String, String> map = new HashMap<String, String>();

        StringBuffer mas = new StringBuffer();
        StringBuffer mas1 = new StringBuffer();
        if (!Files.exists()) {//如果文件夹不存在
            Files.mkdir();//创建文件夹
        }
        try {
            for (PatchEntry patchEntry : patchEntryList) {
                Optional<Filemas> optionalFilemas = filemsgRepository.findById(patchEntry.getFileid());
                Optional<Patchtype> patchtype = patchtypeRepository.findById(patchEntry.getType());
                map.put(patchtype.get().getName(), patchtype.get().getUrl());
                mas1.append(patchEntry.getName() + ":" + patchEntry.getRemarks() + "\r\n");
                File Filess = new File(Files.toString() + "/" + patchtype.get().getName());
                if (!Filess.exists()) {//如果文件夹不存在
                    Filess.mkdir();//创建文件夹
                }
                File fileDir = new File(optionalFilemas.get().getUrl());
                File newFile = new File(Filess.toString() + "/" + patchEntry.getName() + "." + optionalFilemas.get().getType());

                FileUtils.copyFile(fileDir, newFile);


            }
            //创建部署说明
            File bufile = new File(Files.toString() + "/部署说明.txt");
            bufile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(bufile));
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                mas.append(mapKey).append("\r\n").append(mapValue).append("\r\n");
            }
            out.write(mas.toString()); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            //创建补丁说明
            File bfile = new File(Files.toString() + "/补丁说明.txt");
            bfile.createNewFile();
            BufferedWriter out1 = new BufferedWriter(new FileWriter(bfile));
            out1.write(mas1.toString()); // \r\n即为换行
            out1.flush(); // 把缓存区内容压入文件
            out1.close(); // 最后记得关闭文件
            //压缩文件
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            File file = new File("F:/patchcheck/" +  simpleDateFormat.format(date) + ".zip");
            FileOutputStream fos1 = new FileOutputStream(file);
            ZipUtils.toZip(Files.toString(), fos1, true);
            //把压缩包上传到文件服务器
            FTPUtil.uploadFile(Lists.newArrayList(file));
            //存入数据库
            Filemas filemas = new Filemas();
            //TODO 现在写死的，以后要改
            filemas.setUrl("ftp://localhost/"+file.getName());
            filemas.setType("zip");
            filemas.setName(file.getName());
            Filemas optionalFilemas = filemsgRepository.save(filemas);
            //更新补丁表文件路径
            patch.get().setRealUrl(optionalFilemas.getId());

            //增加日志
            patchLog patchLog = new patchLog();
            patchLog.setPatchid(patch.get().getId());
            patchLog.setUserid(user.getId());
            patchLog.setUpdateinfo(user.getName() + "生成里一个补丁压缩包，压缩包名称为" + file.getName());
            patchLogRepository.save(patchLog);
        } catch (IOException e) {
            //删除所有数据
            return false;
        }
        return true;
    }


    @Override
    public String downloadpatch(User user, Integer patchid, HttpServletResponse response) throws IOException {
        Optional<Patch> patch = patchRepository.findById(patchid);
        Optional<Filemas> optionalFilemas = filemsgRepository.findById(patch.get().getRealUrl());
        //增加日志
        patchLog patchLog = new patchLog();
        patchLog.setPatchid(patch.get().getId());
        patchLog.setUserid(user.getId());
        patchLog.setUpdateinfo(user.getName() + "下载了这个补丁");
        patchLogRepository.save(patchLog);
        return optionalFilemas.get().getUrl();
    }

   /* @Override
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
    }*/


}
