package io.fdlessard.codebites.yaas.configuration;

import io.fdlessard.codebites.yaas.gateway.CustomerAccountGateway;
import io.fdlessard.codebites.yaas.gateway.CustomerAccountSpringGateway;
import io.fdlessard.codebites.yaas.properties.CustomerAccountGatewayProperties;
import io.fdlessard.codebites.yaas.gateway.errorhandler.CustomerAccountResponseErrorHandler;
import io.fdlessard.codebites.yaas.gateway.interceptor.DebugClientHttpRequestInterceptor;
import io.fdlessard.codebites.yaas.gateway.interceptor.YaasRequestInterceptor;
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
@Profile("SpringOAuth2Profile")
public class SpringOAuth2ConfigurationProfile {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringOAuth2ConfigurationProfile.class);

    @Autowired
    private CustomerAccountGatewayProperties customerAccountGatewayProperties;

    @Bean
    public CustomerAccountGateway getCustomerAccountGateway() {
        return new CustomerAccountSpringGateway();
    }

    @Bean
    protected OAuth2ProtectedResourceDetails getResourceDetails() {

        LOGGER.info("getResourceDetails()");

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(customerAccountGatewayProperties.getOauth2().getTokenUrl());
        resource.setClientId(customerAccountGatewayProperties.getOauth2().getClient().getId());
        resource.setClientSecret(customerAccountGatewayProperties.getOauth2().getClient().getSecret());
        resource.setScope(customerAccountGatewayProperties.getScopes());

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
        listOfInterceptors.add(new DebugClientHttpRequestInterceptor());

        restTemplate.setInterceptors(listOfInterceptors);

        // Setting the response error handler for the rest template
        restTemplate.setErrorHandler(new CustomerAccountResponseErrorHandler());

        return restTemplate;
    }

}
