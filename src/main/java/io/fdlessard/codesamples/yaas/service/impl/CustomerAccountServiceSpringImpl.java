package io.fdlessard.codesamples.yaas.service.impl;

import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import io.fdlessard.codesamples.yaas.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by fdlessard on 16-10-24.
 */

@Service
public class CustomerAccountServiceSpringImpl implements CustomerAccountService
{

    @Value("${customer.url}")
    private String customerUrl;

    @Value("${tenant}")
    private String tenant;

    @Resource(name = "restTemplate")
    private RestOperations restTemplate;


    @Override
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @Override
    public List<CustomerAccount> getCustomerAccounts() {

        String url = buildUrl();

        List<CustomerAccount> response = restTemplate.getForObject(url, List.class);

        return response;
    }

    private String buildUrl() {
        return customerUrl + "/" + tenant + "/customers";
    }

}
