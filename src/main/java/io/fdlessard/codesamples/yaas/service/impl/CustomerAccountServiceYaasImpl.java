package io.fdlessard.codesamples.yaas.service.impl;

import com.sap.cloud.yaas.servicesdk.authorization.AccessToken;
import com.sap.cloud.yaas.servicesdk.authorization.AuthorizationScope;
import com.sap.cloud.yaas.servicesdk.authorization.DiagnosticContext;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AccessTokenInvalidException;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionCallback;
import com.sap.cloud.yaas.servicesdk.authorization.integration.AuthorizedExecutionTemplate;
import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import io.fdlessard.codesamples.yaas.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
@Service
public class CustomerAccountServiceYaasImpl implements CustomerAccountService
{
    @Value("${customer.url}")
    private String customerUrl;

    @Value("${tenant}")
    private String tenant;

    @Value("${scopes}")
    private String scopes;

    @Autowired
    private AuthorizedExecutionTemplate authorizedExecutionTemplate;

    //@Autowired
    //@Qualifier("yaasRestTemplate")
    @Resource(name = "yaasRestTemplate")
    private RestOperations yaasRestTemplate;

    @Override
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @Override
    public List<CustomerAccount> getCustomerAccounts() {


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

                        List<CustomerAccount> accounts = yaasRestTemplate.getForObject(URI.create(buildUrl()), List.class);

                        return accounts;

                    }
                });

        return response;
    }

    private String buildUrl() {
        return customerUrl + "/" + tenant + "/customers";
    }

}
