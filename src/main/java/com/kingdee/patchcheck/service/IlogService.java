package com.kingdee.patchcheck.service;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.model.patchLog;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * description: IlogService <br>
 * date: 2020\2\4 0008 15:26 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 日志业务逻辑层
 */
public interface IlogService {

    public Page<patchLog> getpatchlog();
    public Page<patchLog> getpatchlog(Integer page, Integer size);
}
