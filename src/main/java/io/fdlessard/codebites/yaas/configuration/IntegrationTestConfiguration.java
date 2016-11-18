package io.fdlessard.codebites.yaas.configuration;

import io.fdlessard.codebites.yaas.properties.CustomerAccountServiceProperties;
import io.fdlessard.codebites.yaas.services.errorhandler.CustomerAccountResponseErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdlessard on 16-10-28.
 */
@Configuration
@Profile("integration")
public class IntegrationTestConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationTestConfiguration.class);

    @Autowired
    private CustomerAccountServiceProperties customerAccountServiceProperties;

    @Bean(name = "customerAccountServiceRestTemplate")
    public RestOperations getCustomerAccountServiceRestTemplate() {

        LOGGER.info("getCustomerAccountServiceRestTemplate()");

        RestTemplate restTemplate = new RestTemplate();

        // Setting the interceptors to add YaaS specific http header properties
        List<ClientHttpRequestInterceptor> listOfInterceptors = new ArrayList<>();
        listOfInterceptors.add(new BasicAuthorizationInterceptor(customerAccountServiceProperties.getBasicAuth().getUsername(), customerAccountServiceProperties.getBasicAuth().getPassword()));
        restTemplate.setInterceptors(listOfInterceptors);

        // Setting the response error handler for the rest template
        restTemplate.setErrorHandler(new CustomerAccountResponseErrorHandler());

        return restTemplate;
    }

}
