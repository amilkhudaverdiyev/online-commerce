package com.onlinefoodcommercems.property;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mail")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailProperty {
    @Value("${mail.username}")
    String username;

}
