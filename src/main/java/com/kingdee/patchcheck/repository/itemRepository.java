package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * description: itemRepository <br>
 * date: 2020\1\7 0007 18:53 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目的数据处理
 */
public interface itemRepository extends JpaRepository<Item,Integer> {
    Page<Item> findByNameLike(String name, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT u.name name,u.department department,u.id from user u, item i, itemand_user iu"
            + " where u.id = iu.userid and i.id =iu.itemid and i.id = ?1")
    List<Object[]> findByIdContaining(Integer id);
    @Query(nativeQuery = true, value = "select u.id id,u.name name,u.department department from user u where u.id not in (select iu.userid from itemand_user iu,item i where iu.itemid = i.id and i.id = ?1) ")
    List<Object[]> findNOByIdContaining(Integer id);
    @Query(nativeQuery = true, value = "SELECT i.name name,i.createtime createtime,i.id id,i.endtime endtime,i.userid userid,i.remarks from user u, item i, itemand_user iu"
            + " where u.id = iu.userid and i.id =iu.itemid and i.closetype = 0 and  iu.userid =  ?1")
    List<Object[]> findByIdContainin1(Integer id);
}
