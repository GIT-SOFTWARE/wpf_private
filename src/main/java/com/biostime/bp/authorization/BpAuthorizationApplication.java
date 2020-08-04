package com.biostime.bp.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.biostime.bp.authorization.config.datasource.properties.DataSourceProperties;
import com.biostime.bp.authorization.config.datasource.properties.DriudDataSourceProperties;
import com.biostime.bp.authorization.config.datasource.properties.DruidPluginProperties;
import com.biostime.jwt.client.bpa.annotation.EnableJwtClient;

@SpringBootApplication
@EnableConfigurationProperties({DriudDataSourceProperties.class,DataSourceProperties.class,DruidPluginProperties.class})
@EnableJwtClient
public class BpAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpAuthorizationApplication.class, args);
    }

}

