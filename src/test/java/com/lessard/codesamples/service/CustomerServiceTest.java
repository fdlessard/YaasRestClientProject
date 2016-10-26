package com.lessard.codesamples.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<Object> s = customerService.getAllCustomers();
        System.out.println("Reponse: " + s);
    }

}