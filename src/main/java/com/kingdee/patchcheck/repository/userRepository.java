package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * description: userRepository <br>
 * date: 2020\1\7 0007 19:06 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 用户数据操作
 */
public interface userRepository extends JpaRepository<User,Integer> {
    List<User> findByNameAndPassword(String name,String password);
    @Query(value = "select s from User as s where s.name like concat('%',?1,'%')")
    Page<User> findByNameIsLike(String name,Pageable pageable);
    Page<User> findByNameLike(String name,Pageable pageable);

}
