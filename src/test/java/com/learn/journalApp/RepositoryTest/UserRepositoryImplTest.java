package com.learn.journalApp.RepositoryTest;

import com.learn.journalApp.entity.User;
import com.learn.journalApp.repository.UserRepositoryImpl;
import com.learn.journalApp.serviceTest.UserArgumentsProvider;
import com.learn.journalApp.services.UserServices;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.getUserForSA());
    }
}
