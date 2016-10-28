package io.fdlessard.codesamples.yaas.service.impl;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.AuthorizationScope;
import com.sap.cloud.yaas.servicesdk.authorization.integration.jaxrs.OAuth2Filter;
import io.fdlessard.codesamples.yaas.domain.Account;
import io.fdlessard.codesamples.yaas.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
@Service
public class AccountServiceJaxRsImpl implements AccountService {

    @Value("${customer.url}")
    private String customerUrl;

    @Value("${scopes}")
    private String scopes;

    @Value("${tenant}")
    private String tenant;

    @Autowired
    private AccessTokenProvider accessTokenProvider;

    @Override
    public List<Account> getAccounts() {

        String[] splitScopes = scopes.split(",");
        final OAuth2Filter oAuth2Filter = new OAuth2Filter(accessTokenProvider, new AuthorizationScope(tenant, Arrays.asList(splitScopes)), 1);
        final Client client = ClientBuilder.newClient().register(oAuth2Filter);

        GenericType<List<Account>> accountListType = new GenericType<List<Account>>() {
        };

        List<Account> reponse  = client.target(customerUrl)
                .request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                .property(OAuth2Filter.PROPERTY_AUTHORIZATION_SCOPE, new AuthorizationScope(tenant, Arrays.asList(splitScopes)))
                .get(accountListType);


        return null;
    }


}