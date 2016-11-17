package io.fdlessard.codebites.yaas.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by fdlessard on 16-11-04.
 */
@ConfigurationProperties(locations = "hcpSpecific.properties", prefix = "basic.auth")
@Component
public class BasicAuthProperties {

    private String basicAuthUsername;

    private String basicAuthPassword;

    public String getBasicAuthUsername() {
        return basicAuthUsername;
    }

    public void setBasicAuthUsername(String basicAuthUsername) {
        this.basicAuthUsername = basicAuthUsername;
    }

    public String getBasicAuthPassword() {
        return basicAuthPassword;
    }

    public void setBasicAuthPassword(String basicAuthPassword) {
        this.basicAuthPassword = basicAuthPassword;
    }
}
