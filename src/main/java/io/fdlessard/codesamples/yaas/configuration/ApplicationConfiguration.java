package io.fdlessard.codesamples.yaas.configuration;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.cache.SimpleCachingProviderWrapper;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionTemplate;
import com.sap.cloud.yaas.servicesdk.authorization.protocol.ClientCredentialsGrantProvider;
import io.fdlessard.codesamples.yaas.service.impl.YaasRequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
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
@PropertySource("classpath:yaas.properties")
public class ApplicationConfiguration {

    @Value("${oauth2.token.url}")
    private String oauth2TokenUrl;

    @Value("${oauth2.client.id}")
    private String oauth2ClientId;

    @Value("${oauth2.client.secret}")
    private String oauth2ClientSecret;

    @Value("${scopes}")
    private String scopes;

    @Bean(name = "restTemplate")
    public RestOperations getRestTemplate() {

        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));

        List<ClientHttpRequestInterceptor> listOfInterceptors = new ArrayList<>();
        listOfInterceptors.add(new YaasRequestInterceptor());
        restTemplate.setInterceptors(listOfInterceptors);
        return restTemplate;
    }

    @Bean(name = "yaasRestTemplate")
    public RestOperations getYaasRestTemplate() {
        return new RestTemplate();
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

    @Bean
    public AccessTokenProvider accessTokenProvider() {

        final ClientCredentialsGrantProvider clientCredentialsGrantProvider = new ClientCredentialsGrantProvider();

        clientCredentialsGrantProvider.setClientId(oauth2ClientId);
        clientCredentialsGrantProvider.setClientSecret(oauth2ClientSecret);
        clientCredentialsGrantProvider.setTokenEndpointUri(URI.create(oauth2TokenUrl));

        return new SimpleCachingProviderWrapper(clientCredentialsGrantProvider);
    }

    @Bean
    public AuthorizedExecutionTemplate authorizedExecutionTemplate() {
        return new AuthorizedExecutionTemplate(accessTokenProvider());
    }

}
