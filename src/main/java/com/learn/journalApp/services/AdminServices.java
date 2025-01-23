package com.learn.journalApp.services;

import com.learn.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminServices {

    @Autowired
    private UserServices services;

    public List<User> getAllUsers(){
        return services.getAll();
    }

    public void createAdminUser(User user){
        services.saveAdminUser(user);
    }

}
