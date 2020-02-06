package com.kingdee.patchcheck.service;

import com.kingdee.patchcheck.VO.UserVO;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.repository.userRepository;
import com.kingdee.patchcheck.service.impl.userServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * description: IuserService <br>
 * date: 2020\1\8 0008 13:47 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 用户业务逻辑层
 */
public interface IuserService {
    /**
     * 校验用户是否存在
     * @param user
     * @return
     */
    public List<User> checkexists(User user);
    public List<User> getAlluser();
    public Page<User> getuser(Integer page, Integer size);
    public Boolean addyuser(User user);
    public Boolean deleteuser(Integer id);
    public Optional<User> getuserbyid(Integer userid);
    public Page<User> getuserbyname(String name);
    public Boolean updateuser(User user);
}
