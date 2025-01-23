package com.learn.journalApp.RepositoryTest;

import com.learn.journalApp.repository.UserRepositoryImpl;
import com.learn.journalApp.services.EmailServices;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmailServicesTest {

    @Autowired
    private EmailServices emailServices;

    @Test
    public void testFindByUserName(){
        emailServices.sendEmail("iamcypherbot@gamil.com",
                "Testing Journal App Email Sevices",
                "Journal App Emails Service Working Successfully");
    }
}
