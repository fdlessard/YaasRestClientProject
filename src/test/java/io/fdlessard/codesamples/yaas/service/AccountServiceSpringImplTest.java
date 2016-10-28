package io.fdlessard.codesamples.yaas.service;

import io.fdlessard.codesamples.yaas.service.impl.AccountServiceSpringImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by fdlessard on 16-10-24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AccountServiceSpringImplTest {

    @Autowired
    private AccountServiceSpringImpl customerServiceSpringImpl;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllCustomers() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<Object> s = customerServiceSpringImpl.getAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("222222222222222222222222222222222222222222222222222");
        s = customerServiceSpringImpl.getAccounts();
        System.out.println("Reponse: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        s = customerServiceSpringImpl.getAccounts();
        System.out.println("Reponse: " + s);
    }

}