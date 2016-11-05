package io.fdlessard.codesamples.yaas.configuration;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.cache.SimpleCachingProviderWrapper;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionTemplate;
import com.sap.cloud.yaas.servicesdk.authorization.protocol.ClientCredentialsGrantProvider;
import io.fdlessard.codesamples.yaas.properties.BasicAuthProperties;
import io.fdlessard.codesamples.yaas.properties.OAuth2Properties;
import io.fdlessard.codesamples.yaas.services.errorhandler.CustomerAccountResponseErrorHandler;
import io.fdlessard.codesamples.yaas.services.interceptor.YaasRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fdlessard on 16-10-28.
 */
@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:yaasSpecific.properties")
@PropertySource("classpath:hcpSpecific.properties")
public class ApplicationConfiguration {

    @Autowired
    private BasicAuthProperties basicAuthProperties;

    @Autowired
    private OAuth2Properties oAuth2Properties;

    @Bean
    protected OAuth2ProtectedResourceDetails getResourceDetails() {

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(oAuth2Properties.getOauth2().getTokenUrl());
        resource.setClientId(oAuth2Properties.getOauth2().getClient().getId());
        resource.setClientSecret(oAuth2Properties.getOauth2().getClient().getSecret());
        resource.setScope(oAuth2Properties.getScopes());

        return resource;
    }

    @Bean(name = "customerAccountServiceRestTemplate")
    public RestOperations getCustomerAccountServiceRestTemplate() {

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

    //@Bean( TODO later )
    public RestOperations getCustomerAccountServiceRestTemplate2() {

        RestTemplate restTemplate = new RestTemplate();

        // Setting the interceptors to add YaaS specific http header properties
        List<ClientHttpRequestInterceptor> listOfInterceptors = new ArrayList<>();
        listOfInterceptors.add(new BasicAuthorizationInterceptor(basicAuthProperties.getBasicAuthUsername(), basicAuthProperties.getBasicAuthPassword()));
        restTemplate.setInterceptors(listOfInterceptors);

        // Setting the response error handler for the rest template
        restTemplate.setErrorHandler(new CustomerAccountResponseErrorHandler());

        return restTemplate;
    }

    @Bean
    public AccessTokenProvider getAccessTokenProvider() {

        ClientCredentialsGrantProvider clientCredentialsGrantProvider = new ClientCredentialsGrantProvider();

        clientCredentialsGrantProvider.setClientId(oAuth2Properties.getOauth2().getClient().getId());
        clientCredentialsGrantProvider.setClientSecret(oAuth2Properties.getOauth2().getClient().getSecret());
        clientCredentialsGrantProvider.setTokenEndpointUri(URI.create(oAuth2Properties.getOauth2().getTokenUrl()));

        return new SimpleCachingProviderWrapper(clientCredentialsGrantProvider);
    }

    @Bean
    public AuthorizedExecutionTemplate getAuthorizedExecutionTemplate() {
        return new AuthorizedExecutionTemplate(getAccessTokenProvider());
    }

    @Bean(name = "customerAccountServiceYaasRestTemplate")
    public RestOperations getYaasRestTemplate() {
        return new RestTemplate();
    }

}
