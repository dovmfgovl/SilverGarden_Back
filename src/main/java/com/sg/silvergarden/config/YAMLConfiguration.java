package com.sg.silvergarden.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@Data
@ConfigurationProperties
public class YAMLConfiguration {
    private String uploadPath;
}
