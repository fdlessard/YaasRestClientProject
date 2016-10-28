package io.fdlessard.codesamples.yaas.service;

import io.fdlessard.codesamples.yaas.domain.Account;
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
public class AccountServiceTest {

    @Autowired
    @Qualifier("accountServiceSpringImpl")
    private AccountService accountServiceSpring;

    @Autowired
    @Qualifier("accountServiceJaxRsImpl")
    private AccountService accountServiceJaxRs;

    @Autowired
    @Qualifier("accountServiceYaasImpl")
    private AccountService accountServiceYaas;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAccountsWithSpring() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<Account> s = accountServiceSpring.getAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("222222222222222222222222222222222222222222222222222");
        s = accountServiceSpring.getAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        s = accountServiceSpring.getAccounts();
        System.out.println("Reponse: " + s);
    }


    @Test
    public void getAccountsWithJaxRs() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<Account> s = accountServiceJaxRs.getAccounts();
        System.out.println("Reponse: " + s);

/*        System.out.println("222222222222222222222222222222222222222222222222222");
        s = accountServiceJaxRsImpl.getAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        s = accountServiceJaxRsImpl.getAccounts();
        System.out.println("Reponse: " + s);*/
    }

    @Test
    public void getAccountsWithYaas() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<Account> s = accountServiceYaas.getAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("222222222222222222222222222222222222222222222222222");
        s = accountServiceYaas.getAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        s = accountServiceYaas.getAccounts();
        System.out.println("Reponse: " + s);
    }

}