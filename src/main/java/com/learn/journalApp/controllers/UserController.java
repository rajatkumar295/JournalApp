package com.learn.journalApp.controllers;

import com.learn.journalApp.APIResponse.WeatherResponse;
import com.learn.journalApp.entity.User;
import com.learn.journalApp.services.UserServices;
import com.learn.journalApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices services;

    @Autowired
    private WeatherService weatherService;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        services.updateUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        services.deleteUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse greetings = weatherService.getWeather("Chandigarh");
        String greetingResponse = "";
        if(greetings!=null){
            greetingResponse = Objects.toString(greetings.getCurrent().getFeelslike());
        }
        return new ResponseEntity<>("Hi "+ authentication.getName() + " Weather feels like: " + greetingResponse ,HttpStatus.OK);
    }
}
