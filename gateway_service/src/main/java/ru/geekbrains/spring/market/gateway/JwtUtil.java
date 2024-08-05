package ru.geekbrains.spring.market.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;


    public Claims getAllClaimsFromToken(String token){
        // в этом методе мы вытаскиваем все Клеймсы (т.е. всю полезную нагрузку)
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

    }


    private boolean isTokenExpired(String token){
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
        // проверяем, что токен еще живой
    }

    public boolean isInvalid(String token){
        return  this.isTokenExpired(token);
        // проверяем, что токен валидный


    }


}
