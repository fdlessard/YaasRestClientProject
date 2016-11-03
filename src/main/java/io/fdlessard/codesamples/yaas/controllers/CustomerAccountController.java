package io.fdlessard.codesamples.yaas.controllers;

import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import io.fdlessard.codesamples.yaas.services.CustomerAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fdlessard on 16-11-02.
 */
@RestController
public class CustomerAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAccountController.class);

    @Autowired
    @Qualifier("customerAccountServiceSpringImpl")
    private CustomerAccountService customerAccountService;

    @RequestMapping(value = "/isAlive", method = RequestMethod.GET, produces = "application/json")
    public String isAlive() {

        LOGGER.debug("CustomerAccountService.isAlive()");
        return "Hello World from CustomerAccountController";
    }

    @RequestMapping(value = "/customerAccounts", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerAccount> getCustomerAccounts() {
        LOGGER.debug("CustomerAccountService.getCustomerAccounts()");
        return customerAccountService.getCustomerAccounts();
    }
}
