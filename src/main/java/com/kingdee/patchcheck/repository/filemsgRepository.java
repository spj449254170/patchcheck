package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.Filemas;
import com.kingdee.patchcheck.model.ItemandUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * description: itemRepository <br>
 * date: 2020\1\7 0007 18:53 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目的数据处理
 */
public interface filemsgRepository extends JpaRepository<Filemas,Integer> {


}
