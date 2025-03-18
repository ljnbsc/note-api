package com.shisan.note.config.security;

import com.alibaba.fastjson2.JSONObject;
import com.shisan.note.service.impl.UserLoginService;
import com.shisan.note.utils.JwtUtil;
import io.jsonwebtoken.Claims;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
 
    @Resource
    JwtUtil jwtUtil;
 
    @Resource
    UserLoginService userLoginService;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token
        String jwtToken = request.getHeader("Authorization");
        CustomHttpServletRequest servletRequest = new CustomHttpServletRequest(request);
        if (jwtToken != null && !jwtToken.isEmpty() && jwtUtil.checkToken(jwtToken)){
            try {
                //token可用
                Claims claims = jwtUtil.getTokenBody(jwtToken);
                String userName = (String) claims.get("userName");
                UserDetails user = userLoginService.loadUserByUsername(userName);
                if (user != null){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                // 设置用户信息
                HashMap<String, Object> map = new HashMap<>();
                map.put("userName", userName);
                map.put("userId", claims.get("userId", Long.class));
                servletRequest.addHeader("user", JSONObject.toJSONString(map));
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }else {
            log.warn("token is null or empty or out of time, probably user is not log in !");
        }
        //继续过滤
        filterChain.doFilter(servletRequest, response);
    }
}