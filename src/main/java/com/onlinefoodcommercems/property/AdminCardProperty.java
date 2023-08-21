package com.onlinefoodcommercems.property;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCardProperty {
    @Value("${admin.card-number}")
    Long cardNumber;
    @Value("${admin.expiry-date}")
    LocalDate expiryDate;
}
