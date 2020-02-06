package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.Patch;
import com.kingdee.patchcheck.model.Patchtype;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * description: patchtypeRepository <br>
 * date: 2020\1\7 0007 19:02 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁类型操作
 */
public interface patchtypeRepository extends JpaRepository<Patchtype,Integer> {

}
