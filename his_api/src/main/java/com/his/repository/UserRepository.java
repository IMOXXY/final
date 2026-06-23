package com.his.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.his.pojo.User;

@Repository     // spring bean
public interface UserRepository extends CrudRepository<User,Integer> {

	// 根据用户名查询用户（Spring Data JPA 自动生成实现）
    User findByUserName(String userName);
}
