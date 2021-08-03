package com.bytework.notificationservice;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("email")
public class EmailProperties {


    private String host;
    private String port;
    private String username;
    private String password;


}
