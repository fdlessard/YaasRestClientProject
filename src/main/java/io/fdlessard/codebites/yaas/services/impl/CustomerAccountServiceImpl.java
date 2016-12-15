package io.fdlessard.codebites.yaas.services.impl;

import io.fdlessard.codebites.yaas.domain.CustomerAccount;
import io.fdlessard.codebites.yaas.gateway.CustomerAccountGateway;
import io.fdlessard.codebites.yaas.gateway.CustomerAccountJaxRsGateway;
import io.fdlessard.codebites.yaas.services.CustomerAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fdlessard on 16-12-11.
 */
@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAccountServiceImpl.class);

    @Autowired
    private CustomerAccountGateway customerAccountGateway;

    @Override
    public List<CustomerAccount> getCustomerAccounts() {

        LOGGER.debug("getCustomerAccounts()");

        return customerAccountGateway.getCustomerAccounts();
    }

}
