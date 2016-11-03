package io.fdlessard.codesamples.yaas.services.impl;

import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import io.fdlessard.codesamples.yaas.services.CustomerAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by fdlessard on 16-10-24.
 */

@Service
public class CustomerAccountServiceSpringImpl implements CustomerAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAccountServiceSpringImpl.class);

    @Value("${customer.url}")
    private String customerUrl;

    @Value("${tenant}")
    private String tenant;

    @Resource(name = "customerAccountServiceRestTemplate")
    private RestOperations customerAccountServiceRestTemplate;


    @Override
    public List<CustomerAccount> getCustomerAccounts() {

        LOGGER.debug("getCustomerAccounts()");

        return customerAccountServiceRestTemplate.getForObject(buildUrl(), List.class, tenant);
    }

    private String buildUrl() {
        return UriComponentsBuilder.fromUriString(customerUrl).buildAndExpand(tenant).toUriString();
    }

}
