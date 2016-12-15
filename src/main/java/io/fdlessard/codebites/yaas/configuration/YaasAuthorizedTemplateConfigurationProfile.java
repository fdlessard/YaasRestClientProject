package io.fdlessard.codebites.yaas.configuration;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.cache.SimpleCachingProviderWrapper;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionTemplate;
import com.sap.cloud.yaas.servicesdk.authorization.protocol.ClientCredentialsGrantProvider;
import io.fdlessard.codebites.yaas.gateway.CustomerAccountGateway;
import io.fdlessard.codebites.yaas.gateway.CustomerAccountYaasGateway;
import io.fdlessard.codebites.yaas.properties.CustomerAccountGatewayProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by fdlessard on 16-10-28.
 */
@Configuration
@Profile("YaasAuthorizedTemplateProfile")
public class YaasAuthorizedTemplateConfigurationProfile {

    private static final Logger LOGGER = LoggerFactory.getLogger(YaasAuthorizedTemplateConfigurationProfile.class);

    @Autowired
    private CustomerAccountGatewayProperties customerAccountGatewayProperties;

    @Bean
    public CustomerAccountGateway getCustomerAccountGateway() {
        return new CustomerAccountYaasGateway();
    }

    @Bean
    public AccessTokenProvider getAccessTokenProvider() {

        LOGGER.info("getAccessTokenProvider()");

        ClientCredentialsGrantProvider clientCredentialsGrantProvider = new ClientCredentialsGrantProvider();

        clientCredentialsGrantProvider.setClientId(customerAccountGatewayProperties.getOauth2().getClient().getId());
        clientCredentialsGrantProvider.setClientSecret(customerAccountGatewayProperties.getOauth2().getClient().getSecret());
        clientCredentialsGrantProvider.setTokenEndpointUri(URI.create(customerAccountGatewayProperties.getOauth2().getTokenUrl()));

        return new SimpleCachingProviderWrapper(clientCredentialsGrantProvider);
    }

    @Bean
    public AuthorizedExecutionTemplate getAuthorizedExecutionTemplate() {

        LOGGER.info("getAuthorizedExecutionTemplate()");
        return new AuthorizedExecutionTemplate(getAccessTokenProvider());
    }

    @Bean(name = "customerAccountServiceYaasRestTemplate")
    public RestOperations getYaasRestTemplate() {
        LOGGER.info("getYaasRestTemplate()");
        return new RestTemplate();
    }

}
