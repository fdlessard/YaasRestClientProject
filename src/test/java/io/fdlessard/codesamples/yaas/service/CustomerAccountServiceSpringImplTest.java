package io.fdlessard.codesamples.yaas.service;

import io.fdlessard.codesamples.yaas.domain.CustomerAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by fdlessard on 16-10-24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CustomerAccountServiceSpringImplTest {

    @Autowired
    @Qualifier("customerAccountServiceSpringImpl")
    private CustomerAccountService customerAccountServiceSpring;

    @Autowired
    @Qualifier("cutsomerAccountServiceJaxRsImpl")
    private CustomerAccountService customerAccountServiceJaxRs;

    @Autowired
    @Qualifier("customerAccountServiceYaasImpl")
    private CustomerAccountService customerAccountServiceYaas;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAccountsWithSpring() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<CustomerAccount> s = customerAccountServiceSpring.getCustomerAccounts();
        System.out.println("Response: " + s);

        System.out.println("222222222222222222222222222222222222222222222222222");
        customerAccountServiceSpring.setTenant("tenanttwo");
        s = customerAccountServiceSpring.getCustomerAccounts();
        System.out.println("Response: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        customerAccountServiceSpring.setTenant("tenantthree");
        s = customerAccountServiceSpring.getCustomerAccounts();
        System.out.println("Response: " + s);
    }


    @Test
    public void getAccountsWithJaxRs() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<CustomerAccount> s = customerAccountServiceJaxRs.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("222222222222222222222222222222222222222222222222222");
        customerAccountServiceJaxRs.setTenant("tenanttwo");
        s = customerAccountServiceJaxRs.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        customerAccountServiceJaxRs.setTenant("tenantthree");
        s = customerAccountServiceJaxRs.getCustomerAccounts();
        System.out.println("Reponse: " + s);
    }

    @Test
    public void getAccountsWithYaas() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<CustomerAccount> s = customerAccountServiceYaas.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("222222222222222222222222222222222222222222222222222");
        customerAccountServiceYaas.setTenant("tenanttwo");
        s = customerAccountServiceYaas.getCustomerAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        customerAccountServiceYaas.setTenant("tenantthree");
        s = customerAccountServiceYaas.getCustomerAccounts();
        System.out.println("Reponse: " + s);
    }

}