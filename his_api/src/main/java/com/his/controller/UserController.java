package com.his.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.his.pojo.ResponseMessage;
import com.his.pojo.User;
import com.his.pojo.dto.UserDto;
import com.his.service.IUserService;
import com.his.utils.TokenUtils;
import com.his.utils.JWTUtils;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;

@RestController   // 接口方法返回对象 转换成json文本
@RequestMapping("/user")    //  localhost:8088/user/**
public class UserController {

    @Autowired
    IUserService userService;

    // REST
    // 增加
    // @Validated：开启参数校验（如果你有加 @NotNull、@Length 之类的注解）
    // @RequestBody：自动将前端 JSON 转为 Java 对象
    @PostMapping  // URL: localhost:8088/user    method: post
    public ResponseMessage add(@Validated @RequestBody UserDto user){
        User userNew = userService.add(user);
        return ResponseMessage.success(userNew);
    }


    // 查询所有
    @GetMapping("/all")    
    public ResponseMessage getAll(){
        return ResponseMessage.success(userService.findAll());
    }


    // 查询
    @GetMapping("/{userId}")   
    public ResponseMessage get(@PathVariable Integer userId){
        User userNew = userService.getUser(userId);
        return ResponseMessage.success(userNew);
    }

    // 修改
    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody UserDto user){
        User userNew = userService.edit(user);
        return ResponseMessage.success(userNew);
    }

    // 删除用户
    // @PathVariable：用于获取 URL 中的路径参数
    @DeleteMapping("/{userId}")
    public ResponseMessage delete(@PathVariable Integer userId){
         userService.delete(userId);
        return ResponseMessage.success();
    }
    
    
    
    @PostMapping("/login")
    public ResponseMessage login(@RequestBody Map<String, String> loginData) {
        String userName = loginData.get("username");
        String password = loginData.get("password");
        User user;
        try {
        	user = userService.login(userName, password);
        } catch (IllegalArgumentException e) {
            // 捕获非法参数异常，返回失败的响应
            return ResponseMessage.error("用户名密码错误");
        } 
        // 生成 token（这里假设你有 TokenUtils 工具类）
        String token = JWTUtils.generateToken(userName);

        Map<String,Object> data = new HashMap<>();
        data.put("user", user);
        data.put("token", token);
        
        return ResponseMessage.success(data);
    }


}
