package com.lessard.codesamples.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;


/**
 * Created by fdlessard on 16-10-24.
 */

@Service
public class CustomerService {

    @Autowired
    private OAuth2RestOperations restTemplate;

    private String tenant = "fdltestproject";


    @Value("customer.url")
    private String customerUrl;

    public String getAllCustomers() {

        String response = restTemplate.getForObject(buildUrl(), String.class);

        return response;

    }

    private String buildUrl() {
        return customerUrl + "/" + tenant + "/customers";
    }

}
