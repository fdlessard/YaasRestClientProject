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
public class CutsomerAccountServiceJaxRsImplTest {

    @Autowired
    @Qualifier("cutsomerAccountServiceJaxRsImpl")
    private CustomerAccountService customerAccountServiceJaxRs;

    @Test
    public void getAccountsWithJaxRs() throws Exception {

        printSeparatorLine("1");
        List<CustomerAccount> s = customerAccountServiceJaxRs.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        printSeparatorLine("2");
        s = customerAccountServiceJaxRs.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        printSeparatorLine("3");
        s = customerAccountServiceJaxRs.getCustomerAccounts();
        System.out.println("Reponse: " + s);
    }

    private void printSeparatorLine(String pattern) {
        String repeated = new String(new char[80]).replace("\0", pattern);
    }

}