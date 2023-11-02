package edu.whu.demo;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.*;

public class JWTTest {
    @Test
    public void testJWT(){
        byte[] secret="db9d654c4860d4d37406d1ffcd92be575".getBytes();  // 设置密钥
        String userName = "yuran";
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        Map<String,Object> infoMap = new HashMap<>();
        infoMap.put("roles", roles);
        // 生成token
        String token = Jwts.builder()
                .setClaims(infoMap) // 自己添加的信息
                .setSubject(userName) // 认证的主体
                .setIssuedAt(new Date(System.currentTimeMillis())) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))// 过期时间
                .signWith(SignatureAlgorithm.HS512, secret) // 加密算法和密钥
                .compact();
        System.out.println("token: " + token);

        Claims claims = Jwts.parser()
                .setSigningKey(secret) // 设置密钥
                .parseClaimsJws(token).getBody(); // 解析，得到一个claims

        System.out.println("claims info: "+claims);
        System.out.println("Subject: "+ claims.getSubject());
        System.out.println("Expiration: "+ claims.getExpiration());
        System.out.println("IssuedAt: "+ claims.getIssuedAt());
        System.out.println("roles: "+ claims.get("roles"));
    }
}