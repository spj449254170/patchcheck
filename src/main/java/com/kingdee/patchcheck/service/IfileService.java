package com.kingdee.patchcheck.service;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * description: IfileService <br>
 * date: 2020\1\8 0008 13:47 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 处理文件业务逻辑层
 */
public interface IfileService {
    String fileUpload(MultipartFile file, String name, Integer pacthid, Integer type, String remarks,User user,Boolean istoin);

}
