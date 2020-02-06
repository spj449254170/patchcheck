package com.kingdee.patchcheck.service.impl;

import com.google.common.collect.Lists;
import com.kingdee.patchcheck.model.*;
import com.kingdee.patchcheck.repository.*;
import com.kingdee.patchcheck.service.IfileService;
import com.kingdee.patchcheck.utils.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description: fileServiceimpl <br>
 * date: 2020\1\8 0008 13:46 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * TODO
 */
@Service
@Transactional
public class fileServiceimpl implements IfileService {

    @Autowired
    private patchEntryRepository patchEntryRepository;
    @Autowired
    private filemsgRepository filemsgRepository;
    @Autowired
    private patchtypeRepository patchtypeRepository;
    @Autowired
    private patchRepository patchRepository;
    @Autowired
    private patchLogRepository patchLogRepository;


    @Override
    public String fileUpload(MultipartFile file, String name, Integer pacthid,  Integer type, String remarks,User user,Boolean istoin){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        int size = (int) file.getSize();
        System.out.println(uploadFileName + "-->" + size);
        Optional<Patch> patch = patchRepository.findById(pacthid);
        //校验文件名是否通过校验
        Optional<Patchtype> patchtype= patchtypeRepository.findById(type);

        Pattern p = Pattern.compile(patchtype.get().getRuler());
        Matcher m = p.matcher(name);
        boolean isValid = m.matches();
        String path = "F:/patchcheck/"+patch;
        File dest = new File(path + "/" + uploadFileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        try {
            PatchEntry patchEntry = new PatchEntry();
            file.transferTo(dest); //保存文件
            //上传FTP服务器
            Boolean aBoolean = FTPUtil.uploadFile(Lists.newArrayList(dest));
            if(!aBoolean){
                return null;
            }
            //创建文件
            Filemas filemas = new Filemas();
            filemas.setName(uploadFileName);
            filemas.setType(fileExtensionName);
            filemas.setUrl(dest.toString());
            Filemas filemas1= filemsgRepository.save(filemas);
            patchEntry.setName(name);
            patchEntry.setRemarks(remarks);
            patchEntry.setType(type);
            patchEntry.setPatchid(pacthid);
            patchEntry.setCreateuserid(user.getId());
            patchEntry.setIstoin(istoin);
            patchEntry.setFileid(filemas1.getId());
            if(isValid){
                patchEntry.setIscheck(true);


            }else{
                patchEntry.setIscheck(false);
            }
            patchEntryRepository.save(patchEntry);

            //增加日志
            patchLog patchLog = new patchLog();
            patchLog.setPatchid(pacthid);
            patchLog.setUserid(user.getId());
            patchLog.setUpdateinfo(user.getName() + "上传一个补丁组件文件，补丁组件名称：" +name+"补丁组件类型：" +fileExtensionName+"补丁组件存放路径"+dest.toString());
            patchLogRepository.save(patchLog);
            return "true";

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }



}
