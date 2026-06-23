package com.his.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.pojo.User;
import com.his.pojo.dto.UserDto;
import com.his.repository.UserRepository;
import java.util.List;

@Service    // spring的bean
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User add(UserDto user) {

        User userPojo=new User();

        BeanUtils.copyProperties(user,userPojo);

        return userRepository.save(userPojo);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new IllegalArgumentException("用户不存在，参数异常!");
        });
    }

    @Override
    public User edit(UserDto user) {
        User userPojo=new User();
        BeanUtils.copyProperties(user,userPojo);

        return userRepository.save(userPojo);
    }

    
    
    @Override
    public User login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("用户名或密码错误！");
        }
        return user;
    }

    
    @Override
    public void delete(Integer userId) {
         userRepository.deleteById(userId);
    }
    
    @Override
    public Iterable<User> findAll() {
    	return userRepository.findAll();
    }
    
}
