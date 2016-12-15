package io.fdlessard.codebites.yaas.gateway;

import com.sap.cloud.yaas.servicesdk.authorization.AccessTokenProvider;
import com.sap.cloud.yaas.servicesdk.authorization.AuthorizationScope;
import com.sap.cloud.yaas.servicesdk.authorization.integration.jaxrs.OAuth2Filter;
import io.fdlessard.codebites.yaas.domain.CustomerAccount;
import io.fdlessard.codebites.yaas.properties.CustomerAccountGatewayProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
public class CustomerAccountJaxRsGateway implements CustomerAccountGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAccountJaxRsGateway.class);

    @Autowired
    private CustomerAccountGatewayProperties customerAccountGatewayProperties;

    @Autowired
    private AccessTokenProvider accessTokenProvider;

    public List<CustomerAccount> getCustomerAccounts() {

        LOGGER.debug("getCustomerAccounts()");

        final OAuth2Filter oAuth2Filter = new OAuth2Filter(accessTokenProvider, new AuthorizationScope(customerAccountGatewayProperties.getTenant(), customerAccountGatewayProperties.getScopes()), 1);
        final Client client = ClientBuilder.newClient().register(oAuth2Filter);

        GenericType<List<CustomerAccount>> accountListType = new GenericType<List<CustomerAccount>>() { };

        List<CustomerAccount> customerAccounts = null;

        try {
            customerAccounts = client.target(buildUrl())
                    .request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE)
                    .property(OAuth2Filter.PROPERTY_AUTHORIZATION_SCOPE, new AuthorizationScope(customerAccountGatewayProperties.getTenant(), customerAccountGatewayProperties.getScopes()))
                    .get(accountListType);

        } catch (NotFoundException e) {
            LOGGER.error("Problem in CutsomerAccountServiceJaxRsImpl()", e);
        }

        return customerAccounts;
    }

    private String buildUrl() {
        return UriComponentsBuilder.fromUriString(customerAccountGatewayProperties.getCustomerUrl()).buildAndExpand(customerAccountGatewayProperties.getTenant()).toUriString();
    }
}