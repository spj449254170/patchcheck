package com.kingdee.patchcheck.config;

import com.kingdee.patchcheck.model.FtpProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpPropertiesConfig {
        @Bean
        @ConfigurationProperties(prefix = "ftp")
        public FtpProperties ftpProperties() {
            return new FtpProperties();
        }

}
