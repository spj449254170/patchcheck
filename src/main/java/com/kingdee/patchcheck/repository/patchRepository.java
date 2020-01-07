package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.patch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description: patchRepository <br>
 * date: 2020\1\7 0007 19:02 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁数据操作
 */
public interface patchRepository extends JpaRepository<patch,String> {
}
