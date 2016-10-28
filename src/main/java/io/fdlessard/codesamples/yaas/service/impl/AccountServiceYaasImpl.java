package io.fdlessard.codesamples.yaas.service.impl;

import io.fdlessard.codesamples.yaas.domain.Account;
import io.fdlessard.codesamples.yaas.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fdlessard on 16-10-27.
 */
@Service
public class AccountServiceYaasImpl implements AccountService
{

    @Value("${customer.url}")
    private String customerUrl;

    @Value("${tenant}")
    private String tenant;

    @Override
    public List<Account> getAccounts() {

        return null;
    }
}
