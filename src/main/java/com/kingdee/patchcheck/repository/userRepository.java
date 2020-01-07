package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description: userRepository <br>
 * date: 2020\1\7 0007 19:06 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 用户数据操作
 */
public interface userRepository extends JpaRepository<user,String> {
}
