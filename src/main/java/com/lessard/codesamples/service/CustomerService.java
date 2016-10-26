package com.lessard.codesamples.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;


/**
 * Created by fdlessard on 16-10-24.
 */

@Service
public class CustomerService {

    @Value("${customer.url}")
    private String customerUrl;

    @Autowired
    private RestOperations restTemplate;
    
    @Value("${tennant}")
    private String tenant;


    public List<Object> getAllCustomers() {

        String url = buildUrl();

        List<Object> response = restTemplate.getForObject(url, List.class);

        return response;
    }

    private String buildUrl() {
        return customerUrl + "/" + tenant + "/customers";
    }

}
