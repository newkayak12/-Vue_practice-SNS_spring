package com.vue.vue_practicesns_backend.common.auth;

import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenManager {

    public String encryptToken(UserDto dto, String salt){

        Map<String,Object> map = new HashMap<>();
                map.put("userNo", dto.getUserNo()); //발급되는 사람 정보
                map.put("userId", dto.getUserId());
                map.put("userName", dto.getUserName());
                map.put("password", dto.getPassword());
                map.put("phone", dto.getPhone());
                map.put("birth", dto.getBirth().toString());
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("Server")  //발급자
                .setIssuedAt(now)  //발급 시간
                .setExpiration(new Date(now.getTime()+ Duration.ofHours(24).toHours())) //Expiration Date
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, salt.getBytes()) //알고리즘 , salt 값
                .compact();
    }

    public Claims decryptToken(String authorizationToken, String salt) throws IllegalAccessException{

        return Jwts.parser()
                .setSigningKey(salt.getBytes())
                .parseClaimsJws(authorizationToken)
                .getBody();
    }
}
