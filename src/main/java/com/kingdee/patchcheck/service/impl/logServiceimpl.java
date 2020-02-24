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
    @Autowired
    private patchLogRepository patchLogRepository;

    @Override
    public Page<patchLog> getpatchlog() {
        List<patchLog> patchLogList = patchLogRepository.findAll();

        PageRequest pageable = PageRequest.of(0, 10);
        return new PageImpl<patchLog>(patchLogList, pageable, patchLogList.size() / 10 + 1);
    }
    @Override
    public Page<patchLog> getpatchlog(Integer page, Integer size) {
        if (null ==page){
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        //Page<User>  userList = userRepository.findAll(pageable);

        Page<patchLog> all = patchLogRepository.findAll(pageable);
        return all;
    }
}
