package io.fdlessard.codebites.yaas.gateway;

import io.fdlessard.codebites.yaas.domain.CustomerAccount;
import io.fdlessard.codebites.yaas.properties.CustomerAccountGatewayProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * Created by fdlessard on 16-10-24.
 */


public class CustomerAccountSpringGateway implements CustomerAccountGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAccountSpringGateway.class);

    @Autowired
    private CustomerAccountGatewayProperties customerAccountGatewayProperties;
    
    @Autowired
    private RestOperations customerAccountServiceRestTemplate;

    public List<CustomerAccount> getCustomerAccounts() {

        LOGGER.debug("getCustomerAccounts()");
        return customerAccountServiceRestTemplate.getForObject(buildUrl(), List.class);
    }
    
    private String buildUrl() {
        return UriComponentsBuilder.fromUriString(customerAccountGatewayProperties.getCustomerUrl()).buildAndExpand(customerAccountGatewayProperties.getTenant()).toUriString();
    }
}
