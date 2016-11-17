package io.fdlessard.codebites.yaas.services;

import io.fdlessard.codebites.yaas.domain.CustomerAccount;

import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
public interface CustomerAccountService {

    List<CustomerAccount> getCustomerAccounts();
}
