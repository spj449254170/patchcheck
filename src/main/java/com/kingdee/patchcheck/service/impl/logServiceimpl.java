package com.kingdee.patchcheck.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.ItemandUser;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.model.patchLog;
import com.kingdee.patchcheck.repository.departmentRepository;
import com.kingdee.patchcheck.repository.itemRepository;
import com.kingdee.patchcheck.repository.itemanduserRepository;
import com.kingdee.patchcheck.repository.patchLogRepository;
import com.kingdee.patchcheck.service.IitemService;
import com.kingdee.patchcheck.service.IlogService;
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
 * description: logServiceimpl <br>
 * date: 2020\2\24 0008 15:28 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 *
 */
@Service
@Transactional
public class logServiceimpl implements IlogService {
    Logger Logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private patchLogRepository patchLogRepository;

    @Override
    public Page<patchLog> getpatchlog() {
        Logger.info("获取日志实现类，入参 无");
        List<patchLog> patchLogList = patchLogRepository.findAll();

        PageRequest pageable = PageRequest.of(0, 10);
        PageImpl<patchLog> patchLog = new PageImpl<patchLog>(patchLogList, pageable, patchLogList.size() / 10 + 1);
        Logger.info("获取日志实现类，出参 patchLog:{}",patchLog);
        return patchLog;
    }
    @Override
    public Page<patchLog> getpatchlog(Integer page, Integer size) {
        Logger.info("分页获取日志实现类，入参page:{},siaz:{}",page,size);
        if (null ==page){
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        //Page<User>  userList = userRepository.findAll(pageable);

        Page<patchLog> all = patchLogRepository.findAll(pageable);
        Logger.info("分页获取日志实现类，出参 all:{}",all.toString());
        return all;
    }
}
