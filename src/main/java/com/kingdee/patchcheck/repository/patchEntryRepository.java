package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.Patch;
import com.kingdee.patchcheck.model.PatchEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * description: patchEntryRepository <br>
 * date: 2020\1\7 0007 19:04 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁详细信息数据操作
 */
public interface patchEntryRepository extends JpaRepository<PatchEntry,Integer> {
    List<PatchEntry> findByPatchidAndIsclose(Integer patchid, Pageable pageable,Boolean isclose);
    List<PatchEntry> findByPatchidAndIscloseAndIscheck(Integer patchid,Boolean isclose,Boolean ischeck);
}
