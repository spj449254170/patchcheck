package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.patchEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description: patchEntryRepository <br>
 * date: 2020\1\7 0007 19:04 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁详细信息数据操作
 */
public interface patchEntryRepository extends JpaRepository<patchEntry,String> {
}
