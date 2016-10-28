package io.fdlessard.codesamples.yaas;

import io.fdlessard.codesamples.yaas.service.impl.YaasRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Configuration
public class YaasRestConnectionProjectApplication {

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
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));


        List<ClientHttpRequestInterceptor> listOfInterceptors = new ArrayList<>();
        listOfInterceptors.add(new YaasRequestInterceptor());
        restTemplate.setInterceptors(listOfInterceptors);
        return restTemplate;
    }

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(oauth2TokenUrl);
        resource.setClientId(oauth2ClientId);
        resource.setClientSecret(oauth2ClientSecret);
        String[] splitScopes = scopes.split(",");
        resource.setScope(Arrays.asList(splitScopes));

        return resource;
    }



/*
    public OAuth2RestOperations restTemplate() {
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));
        AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
        provider.setClientTokenServices(clientTokenServices());
        return template;
    }
*/


    public static void main(String[] args) {
        SpringApplication.run(YaasRestConnectionProjectApplication.class, args);
    }

}
