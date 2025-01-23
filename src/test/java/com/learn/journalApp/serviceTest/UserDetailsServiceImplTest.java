package com.learn.journalApp.serviceTest;

import com.learn.journalApp.entity.User;
import com.learn.journalApp.services.UserDetailsServiceImpl;
import com.learn.journalApp.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserServices userServices;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUserNameTest(){
        when(userServices.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").password("userPass").roles(new ArrayList<>()).build());
        UserDetails user= userDetailsService.loadUserByUsername("abc");
        assertNotNull(user);
    }

}
