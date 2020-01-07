package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.patchLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description: patchLogRepository <br>
 * date: 2020\1\7 0007 19:05 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁日志数据操作
 */
public interface patchLogRepository extends JpaRepository<patchLog,String> {
}
