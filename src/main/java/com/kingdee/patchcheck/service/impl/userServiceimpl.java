package com.kingdee.patchcheck.service.impl;

import com.kingdee.patchcheck.VO.UserVO;
import com.kingdee.patchcheck.model.Department;
import com.kingdee.patchcheck.model.Power;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.repository.departmentRepository;
import com.kingdee.patchcheck.repository.powerRepository;
import com.kingdee.patchcheck.repository.userRepository;
import com.kingdee.patchcheck.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * description: userServiceimpl <br>
 * date: 2020\1\8 0008 13:46 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * TODO
 */
@Service
public class userServiceimpl implements IuserService {
    @Autowired
    private userRepository userRepository;
    @Autowired
    private departmentRepository departmentRepository;
    @Autowired
    private powerRepository powerRepository;
    @Override
    public List<User> checkexists(User user){
        List<User> userList = userRepository.findByNameAndPassword(user.getName(), user.getPassword());

        return userList;

    }

    @Override
    public List<User> getAlluser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public Page<User> getuser(Integer page, Integer size) {
        if (null ==page){
            page = 0;
        }
        PageRequest pageable = PageRequest.of(page, size);
        //Page<User>  userList = userRepository.findAll(pageable);

        Page<User> all = userRepository.findAll(pageable);
        return all;
    }

    @Override
    public Boolean addyuser(User user) {
        User save = userRepository.save(user);
        if(null == save){
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteuser(Integer id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public  Optional<User> getuserbyid(Integer userid) {
        Optional<User> user1 = userRepository.findById(userid);
        return user1;
    }

    @Override
    public Page<User> getuserbyname(String name) {
        PageRequest pageable = PageRequest.of(0, 1);
        Page<User> users=userRepository.findByNameLike(name,pageable);
        return users;
    }

    @Override
    public Boolean updateuser(User user) {
        User updateuser = new User();
        updateuser.setId(user.getId());
        updateuser.setDepartment(user.getDepartment());
        updateuser.setName(user.getName());
        updateuser.setPassword(user.getPassword());
        updateuser.setPower(user.getPower());
        updateuser.setPhone(user.getPhone());
        Optional<User> users=userRepository.findById(user.getId());
        updateuser.setCreateTime(users.get().getCreateTime());
        User user1 =  userRepository.save(updateuser);
        if(null == user1){
            return false;
        }else{
            return true;
        }
    }


}
