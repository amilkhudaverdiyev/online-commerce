package com.onlinefoodcommercems.property;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtProperty {
    @Value("${jwt.header}")
    String header;
    @Value("${jwt.prefix}")
    String prefix;
    @Value("${jwt.expiration}")
    int expiration;
    @Value("${jwt.secretKey}")
    String secretKey;
}
