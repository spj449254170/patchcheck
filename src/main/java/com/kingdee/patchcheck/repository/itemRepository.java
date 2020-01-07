package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description: itemRepository <br>
 * date: 2020\1\7 0007 18:53 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目的数据处理
 */
public interface itemRepository extends JpaRepository<item,String> {
}
