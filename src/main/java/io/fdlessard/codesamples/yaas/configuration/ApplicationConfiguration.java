package io.fdlessard.codesamples.yaas.configuration;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.cache.SimpleCachingProviderWrapper;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionTemplate;
import com.sap.cloud.yaas.servicesdk.authorization.protocol.ClientCredentialsGrantProvider;
import io.fdlessard.codesamples.yaas.services.errorhandler.CustomerAccountResponseErrorHandler;
import io.fdlessard.codesamples.yaas.services.interceptor.YaasRequestInterceptor;
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

    @Value("${basic.auth.username}")
    private String basicAuthUsername;

    @Value("${basic.auth.password}")
    private String basicAuthPassword;

    @Value("${oauth2.token.url}")
    private String oauth2TokenUrl;

    @Value("${oauth2.client.id}")
    private String oauth2ClientId;

    @Value("${oauth2.client.secret}")
    private String oauth2ClientSecret;

    @Value("${scopes}")
    private String scopes;

    @Bean
    protected OAuth2ProtectedResourceDetails getResourceDetails() {

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(oauth2TokenUrl);
        resource.setClientId(oauth2ClientId);
        resource.setClientSecret(oauth2ClientSecret);
        String[] splitScopes = scopes.split(",");
        resource.setScope(Arrays.asList(splitScopes));

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
        listOfInterceptors.add(new BasicAuthorizationInterceptor(basicAuthUsername, basicAuthPassword));
        restTemplate.setInterceptors(listOfInterceptors);

        // Setting the response error handler for the rest template
        restTemplate.setErrorHandler(new CustomerAccountResponseErrorHandler());

        return restTemplate;
    }

    @Bean
    public AccessTokenProvider getAccessTokenProvider() {

        ClientCredentialsGrantProvider clientCredentialsGrantProvider = new ClientCredentialsGrantProvider();

        clientCredentialsGrantProvider.setClientId(oauth2ClientId);
        clientCredentialsGrantProvider.setClientSecret(oauth2ClientSecret);
        clientCredentialsGrantProvider.setTokenEndpointUri(URI.create(oauth2TokenUrl));

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
