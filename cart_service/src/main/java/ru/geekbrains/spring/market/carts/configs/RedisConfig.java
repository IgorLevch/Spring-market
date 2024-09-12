package ru.geekbrains.spring.market.carts.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories  //  включаются редис репозитории
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){   // создается  factory-bean, особо не конфигурируется, т.к.коннектится на стандартный порт
        return new JedisConnectionFactory();

    }


    // ниже, для того, чтобы хранить что-то в редисе удобнее в кач-ве ключей использовать строки (например тот же uuid корзины или имя польз-ля)
    // А в кач-ве значения: объекты. Этими объектам будут джейсоны.
    @Bean
    public RedisTemplate<String, Object> redisTemplate(){

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory());

        return template;


    }



}
