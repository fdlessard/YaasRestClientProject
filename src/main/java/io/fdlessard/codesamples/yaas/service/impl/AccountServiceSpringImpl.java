package io.fdlessard.codesamples.yaas.service.impl;

import io.fdlessard.codesamples.yaas.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;


/**
 * Created by fdlessard on 16-10-24.
 */

@Service
public class AccountServiceSpringImpl implements AccountService {

    @Value("${customer.url}")
    private String customerUrl;

    @Value("${tennant}")
    private String tenant;

    @Autowired
    private RestOperations restTemplate;


    @Override
    public List<Object> getAccounts() {

        String url = buildUrl();

        List<Object> response = restTemplate.getForObject(url, List.class);

        return response;
    }

    private String buildUrl() {
        return customerUrl + "/" + tenant + "/customers";
    }

}
