package com.shisan.note.utils;

import com.shisan.note.entity.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    //常量
    public static final long EXPIRE = 1000 * 60 * 60 * 4; //token过期时间,4个小时
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHOOISOKSkmslmgdsSFSDSDGGSdsdglsdkgslkdngasdsdasgsjyttyj"; //秘钥

    /**
     * 生成token字符串的方法
     * @param loginUser 登录用户信息
     * @return token
     */
    public String generateToken(LoginUser loginUser) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("userName", loginUser.getUsername()).claim("userId", loginUser.getUserId())//设置token主体部分 ，存储用户信息
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 验证token字符串是否是有效的  包括验证是否过期
     *
     * @param jwtToken token
     * @return true 有效，false 国企
     */
    public boolean checkToken(String jwtToken) {
        if (jwtToken == null || jwtToken.isEmpty()) {
            log.error("Jwt is empty");
            return false;
        }
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            Claims body = claims.getBody();
            return body.getExpiration().after(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public Claims getTokenBody(String jwtToken) {
        if (jwtToken == null || jwtToken.isEmpty()) {
            log.error("Jwt is empty");
            return null;
        }
        try {
            return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}