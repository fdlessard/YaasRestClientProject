package com.lessard.codesamples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Configuration
public class YaasRestConnectionProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(YaasRestConnectionProjectApplication.class, args);
	}


    @Value("${oauth2.token.url}")
    private String oauth2TokenUrl;

    @Value("${oauth2.client.id}")
    private String oauth2ClientId;

    @Value("${oauth2.client.secret}")
    private String oauth2ClientSecret;

    @Value("${scopes}")
    private String scopes;


    @Bean
    public RestOperations getRestTemplate() {

        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
    }

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        resource.setAccessTokenUri(oauth2TokenUrl);
        resource.setClientId(oauth2ClientId);
        resource.setClientSecret(oauth2ClientSecret);
        resource.setScope(Arrays.asList(scopes));

        //resource.setGrantType("password");
        resource.setUsername("toto");
        resource.setPassword("toto");


        return resource;
    }

}
