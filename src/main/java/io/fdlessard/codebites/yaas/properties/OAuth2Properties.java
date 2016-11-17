package io.fdlessard.codebites.yaas.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fdlessard on 16-11-04.
 */
@ConfigurationProperties(locations = "yaasSpecific.properties")
@Component
public class OAuth2Properties {
    
    private String tenant;

    @Value("#{'${scopes}'.split(',')}")
    private List<String> scopes;

    private Oauth2 oauth2;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public Oauth2 getOauth2() {
        return oauth2;
    }

    public void setOauth2(Oauth2 oauth2) {
        this.oauth2 = oauth2;
    }

    public static class Oauth2 {

        private String tokenUrl;
        private Client client;

        public String getTokenUrl() {
            return tokenUrl;
        }

        public void setTokenUrl(String tokenUrl) {
            this.tokenUrl = tokenUrl;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public static class Client {

            private String id;
            private String secret;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }
        }
    }
}
