package io.fdlessard.codesamples.yaas;

import io.fdlessard.codesamples.yaas.service.impl.YaasRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Configuration
public class YaasRestConnectionProjectApplication {





    public static void main(String[] args) {
        SpringApplication.run(YaasRestConnectionProjectApplication.class, args);
    }

}
