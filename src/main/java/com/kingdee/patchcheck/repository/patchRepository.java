package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.Patch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * description: patchRepository <br>
 * date: 2020\1\7 0007 19:02 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁数据操作
 */
public interface patchRepository extends JpaRepository<Patch,Integer> {
    List<Patch> findByItemid(Integer itemid, Pageable pageable);
}
