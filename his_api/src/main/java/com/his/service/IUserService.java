package com.his.service;

import com.his.pojo.User;
import com.his.pojo.dto.UserDto;

import java.util.List;

public interface IUserService {
    /**
     * 插入用户
     *
     * @param user 参数
     * @return
     */
    User add(UserDto user);

    /**
     * 查询用户
     * @param userId 用户id
     * @return
     */
    User getUser(Integer userId);

    /**
     * 修改用户
     * @param user 修改用户对象
     * @return
     */
    User edit(UserDto user);
    
    /**
     * 用户登录
     */
    User login(String userName, String password);
    
    

    /**
     * 删除
     *
     * @param userId
     */
    void delete(Integer userId);
    
    
    Iterable<User> findAll();
}
