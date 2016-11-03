package io.fdlessard.codesamples.yaas.services.impl;

import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import io.fdlessard.codesamples.yaas.services.CustomerAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by fdlessard on 16-10-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class CustomerAccountServiceYaasImplTest {

    @Autowired
    @Qualifier("customerAccountServiceYaasImpl")
    private CustomerAccountService customerAccountServiceYaas;

    @Test
    public void getAccountsWithYaas() throws Exception {

        printSeparatorLine("1");
        List<CustomerAccount> s = customerAccountServiceYaas.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        printSeparatorLine("2");
        s = customerAccountServiceYaas.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        printSeparatorLine("3");
        s = customerAccountServiceYaas.getCustomerAccounts();
        System.out.println("Reponse: " + s);
    }


    private void printSeparatorLine(String pattern) {
        String repeated = new String(new char[80]).replace("\0", pattern);
    }

}