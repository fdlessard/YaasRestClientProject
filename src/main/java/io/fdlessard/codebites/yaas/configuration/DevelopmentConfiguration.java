package io.fdlessard.codebites.yaas.configuration;

import io.fdlessard.codebites.yaas.properties.CustomerAccountServiceProperties;
import io.fdlessard.codebites.yaas.services.errorhandler.CustomerAccountResponseErrorHandler;
import io.fdlessard.codebites.yaas.services.interceptor.YaasRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdlessard on 16-10-28.
 */
@Configuration
@Profile("development")
public class DevelopmentConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevelopmentConfiguration.class);

    @Autowired
    private CustomerAccountServiceProperties customerAccountServiceProperties;

    @Bean
    protected OAuth2ProtectedResourceDetails getResourceDetails() {

        LOGGER.info("getResourceDetails()");

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(customerAccountServiceProperties.getOauth2().getTokenUrl());
        resource.setClientId(customerAccountServiceProperties.getOauth2().getClient().getId());
        resource.setClientSecret(customerAccountServiceProperties.getOauth2().getClient().getSecret());
        resource.setScope(customerAccountServiceProperties.getScopes());

        return resource;
    }

    @Bean(name = "customerAccountServiceRestTemplate")
    public RestOperations getCustomerAccountServiceRestTemplate() {

        LOGGER.info("getCustomerAccountServiceRestTemplate()");

        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(getResourceDetails(), new DefaultOAuth2ClientContext(atr));

        // Setting the interceptors to add YaaS specific http header properties
        List<ClientHttpRequestInterceptor> listOfInterceptors = new ArrayList<>();
        listOfInterceptors.add(new YaasRequestInterceptor());
        restTemplate.setInterceptors(listOfInterceptors);

        // Setting the response error handler for the rest template
        restTemplate.setErrorHandler(new CustomerAccountResponseErrorHandler());

        return restTemplate;
    }

}
