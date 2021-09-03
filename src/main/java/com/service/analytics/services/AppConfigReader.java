package com.service.analytics.services;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
public class AppConfigReader {
    private String mypath;

    public String getMypath() {
        return mypath;
    }

    public void setMypath(String mypath) {
        this.mypath = mypath;
    }
}
