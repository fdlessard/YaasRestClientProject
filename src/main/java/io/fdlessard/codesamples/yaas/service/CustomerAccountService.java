package io.fdlessard.codesamples.yaas.service;

import io.fdlessard.codesamples.yaas.domain.CustomerAccount;

import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
public interface CustomerAccountService {

    List<CustomerAccount> getCustomerAccounts();
}
