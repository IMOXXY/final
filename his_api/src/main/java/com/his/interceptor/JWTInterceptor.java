package com.his.interceptor;

import com.his.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	// 允许预检请求通过
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }
    	// 从请求头中获取 token
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid token");
            return false;
        }

        token = token.substring(7); // 去掉 "Bearer "

        if (!JWTUtils.verifyToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token verification failed");
            return false;
        }

        // 你可以把用户名等信息设置进 request attribute 供后续使用
        String username = JWTUtils.getUsername(token);
        request.setAttribute("username", username);

        return true;
    }
}

