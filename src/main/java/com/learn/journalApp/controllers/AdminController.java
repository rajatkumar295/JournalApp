package com.learn.journalApp.controllers;

import com.learn.journalApp.cache.AppCache;
import com.learn.journalApp.entity.User;
import com.learn.journalApp.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServices services;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all_users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users= services.getAllUsers();
        if(users!=null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create_admin_user")
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        services.createAdminUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
     appCache.init();
    }
}
