package io.fdlessard.codebites.yaas.services.impl;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.AuthorizationScope;
import com.sap.cloud.yaas.servicesdk.authorization.integration.jaxrs.OAuth2Filter;
import io.fdlessard.codebites.yaas.domain.CustomerAccount;
import io.fdlessard.codebites.yaas.properties.CustomerAccountServiceProperties;
import io.fdlessard.codebites.yaas.services.CustomerAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.NotFoundException;
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

    @Autowired
    private CustomerAccountServiceProperties customerAccountServiceProperties;

    @Autowired
    private AccessTokenProvider accessTokenProvider;

    @Override
    public List<CustomerAccount> getCustomerAccounts() {

        LOGGER.debug("getCustomerAccounts()");

        final OAuth2Filter oAuth2Filter = new OAuth2Filter(accessTokenProvider, new AuthorizationScope(customerAccountServiceProperties.getTenant(), customerAccountServiceProperties.getScopes()), 1);
        final Client client = ClientBuilder.newClient().register(oAuth2Filter);

        GenericType<List<CustomerAccount>> accountListType = new GenericType<List<CustomerAccount>>() { };

        List<CustomerAccount> customerAccounts = null;

        try {
            customerAccounts = client.target(buildUrl())
                    .request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .property(OAuth2Filter.PROPERTY_AUTHORIZATION_SCOPE, new AuthorizationScope(customerAccountServiceProperties.getTenant(), customerAccountServiceProperties.getScopes()))
                    .get(accountListType);

        } catch (NotFoundException e) {
            LOGGER.error("Problem in CutsomerAccountServiceJaxRsImpl()", e);
        }

        return customerAccounts;
    }

    private String buildUrl() {
        return UriComponentsBuilder.fromUriString(customerAccountServiceProperties.getCustomerUrl()).buildAndExpand(customerAccountServiceProperties.getTenant()).toUriString();
    }
}