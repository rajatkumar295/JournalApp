package com.learn.journalApp.serviceTest;

import com.learn.journalApp.entity.User;
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
public class JournalServiceTest {

    @Autowired
    private UserServices userServices;

    @Test
    public void testFindByUserName(){
        assertNotNull(userServices.findByUserName("Rajat"));
    }

    @ParameterizedTest
    @ValueSource(strings ={
            "Rajat",
            "Sunil",
            "Paras"
    })
    public void TestFindByUserNameWithParam(String name){
        assertNotNull(userServices.findByUserName(name));
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testCreateUser(User user){
        assertTrue(userServices.saveUser(user));
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,3,5",
            "6,7,13"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }

    @BeforeAll
    public static void testBeforeAll(){
        System.out.println("Implementing Before All");
    }

    @BeforeEach
    public void testBeforeEach(){
        System.out.println("Implementing Before Each");
    }

    @AfterAll
    public static void testAfterAll(){
        System.out.println("Implementing After All");
    }

    @AfterEach
    public void testAfterEach(){
        System.out.println("Implementing After Each");
    }

}
