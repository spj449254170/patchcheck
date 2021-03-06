package com.kingdee.patchcheck.repository;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.ItemandUser;
import com.kingdee.patchcheck.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * description: itemRepository <br>
 * date: 2020\1\7 0007 18:53 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目的数据处理
 */
public interface itemanduserRepository extends JpaRepository<ItemandUser,Integer> {
    @Query(value = "delete from itemand_user where userid= ?1 and itemid =?2", nativeQuery = true)
    @Modifying
    void deleteByUserid(Integer userid,Integer itemid);

}
