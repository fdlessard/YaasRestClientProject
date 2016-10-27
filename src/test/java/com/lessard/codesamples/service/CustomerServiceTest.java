package com.lessard.codesamples.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by fdlessard on 16-10-24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllCustomers() throws Exception {

        System.out.println("111111111111111111111111111111111111111111111111111");
        List<Object> s = customerService.getAllCustomers();
        System.out.println("Reponse: " + s);

        System.out.println("222222222222222222222222222222222222222222222222222");
        s = customerService.getAllCustomers();
        System.out.println("Reponse: " + s);

        System.out.println("333333333333333333333333333333333333333333333333333");
        s = customerService.getAllCustomers();
        System.out.println("Reponse: " + s);
    }

}