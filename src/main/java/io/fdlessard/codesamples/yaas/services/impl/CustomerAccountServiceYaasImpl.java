package io.fdlessard.codesamples.yaas.services.impl;

import com.sap.cloud.yaas.servicesdk.authorization.AccessToken;
import com.sap.cloud.yaas.servicesdk.authorization.AuthorizationScope;
import com.sap.cloud.yaas.servicesdk.authorization.DiagnosticContext;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionCallback;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionTemplate;
import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import io.fdlessard.codesamples.yaas.services.CustomerAccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
@Service
public class CustomerAccountServiceYaasImpl implements CustomerAccountService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAccountServiceYaasImpl.class);

    @Value("${customer.url}")
    private String customerUrl;

    @Value("${tenant}")
    private String tenant;

    @Value("${scopes}")
    private String scopes;

    @Autowired
    private AuthorizedExecutionTemplate authorizedExecutionTemplate;

    @Resource(name = "customerAccountServiceYaasRestTemplate")
    private RestOperations customerAccountServiceYaasRestTemplate;


    @Override
    public List<CustomerAccount> getCustomerAccounts() {

        LOGGER.debug("getCustomerAccounts()");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String[] splitScopes = scopes.split(",");

        final List<CustomerAccount> response = authorizedExecutionTemplate.executeAuthorized(
                new AuthorizationScope(tenant, Arrays.asList(splitScopes)),
                new DiagnosticContext("requestId", 0),
                new AuthorizedExecutionCallback<List<CustomerAccount>>()
                {
                    @Override
                    public List<CustomerAccount> execute(final AccessToken token)
                    {
                        // execute requests to other YaaS services with the given token in the "Authorization" header
                        // return Response object
                        String authorization = token.getType() + " " + token.getValue();
                        httpHeaders.add(HttpHeaders.AUTHORIZATION, token.getType() + " " + token.getValue());
                        HttpEntity<String> requestEntity = new HttpEntity<>(StringUtils.EMPTY, httpHeaders);
                        ResponseEntity<List> response =  customerAccountServiceYaasRestTemplate.exchange(URI.create(buildUrl()), HttpMethod.GET, requestEntity, List.class);

                        return response.getBody();

                    }
                });

        return response;
    }

    private String buildUrl() {
        return UriComponentsBuilder.fromUriString(customerUrl).buildAndExpand(tenant).toUriString();
    }

}
