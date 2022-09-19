package com.softserveinc.ita.rozetka.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private final Properties properties;

    public ConfigProperties() throws IOException {
        properties = new Properties();

            var fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
    }

    public String getFacebookUserEmailOrPhone() {
        return properties.getProperty("FACEBOOK_USER_EMAIL_OR_PHONE");
    }

    public String getFacebookUserPassword() {
        return properties.getProperty("FACEBOOK_USER_PASSWORD");
    }

    public String getUserEmail() {
        return properties.getProperty("USER_EMAIL");
    }
}
