package com.his.config;

import com.his.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")               // 拦截所有路径
                .excludePathPatterns("/user/login", "/register", "/error",  "/outvisit/**", "/user/**","/medicalrecord/**","/dictorder/**","/doctororder/**");  // 放行登录、注册等路径
    }
    
    // 全局跨域处理
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 匹配所有接口
                .allowedOriginPatterns("*")  // 允许所有前端源（也可以写 http://localhost:8080）
                .allowedMethods("*")  // 允许的方法
                .allowedHeaders("*")  // 允许的请求头
                .allowCredentials(true);  // 允许携带 Cookie 或 Authorization 头
    }
}
