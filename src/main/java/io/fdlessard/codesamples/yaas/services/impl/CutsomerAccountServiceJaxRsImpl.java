package io.fdlessard.codesamples.yaas.services.impl;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.AuthorizationScope;
import com.sap.cloud.yaas.servicesdk.authorization.integration.jaxrs.OAuth2Filter;
import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import io.fdlessard.codesamples.yaas.services.CustomerAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
@Service
public class CutsomerAccountServiceJaxRsImpl implements CustomerAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CutsomerAccountServiceJaxRsImpl.class);

    @Value("${customer.url}")
    private String customerUrl;

    @Value("${scopes}")
    private String scopes;

    @Value("${tenant}")
    private String tenant;

    @Autowired
    private AccessTokenProvider accessTokenProvider;

    @Override
    public List<CustomerAccount> getCustomerAccounts() {

        LOGGER.debug("getCustomerAccounts()");

        String[] splitScopes = scopes.split(",");
        final OAuth2Filter oAuth2Filter = new OAuth2Filter(accessTokenProvider, new AuthorizationScope(tenant, Arrays.asList(splitScopes)), 1);
        final Client client = ClientBuilder.newClient().register(oAuth2Filter);

        GenericType<List<CustomerAccount>> accountListType = new GenericType<List<CustomerAccount>>() {
        };

        List<CustomerAccount> customerAccounts = client.target(buildUrl())
                .request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                .property(OAuth2Filter.PROPERTY_AUTHORIZATION_SCOPE, new AuthorizationScope(tenant, Arrays.asList(splitScopes)))
                .get(accountListType);

        return customerAccounts;
    }

    private String buildUrl() {
        return UriComponentsBuilder.fromUriString(customerUrl).buildAndExpand(tenant).toUriString();
    }
}