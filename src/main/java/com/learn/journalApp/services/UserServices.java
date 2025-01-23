package com.learn.journalApp.services;

import com.learn.journalApp.entity.User;
import com.learn.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserServices {

    @Autowired
    private UserRepository repository;

    @Autowired
    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public List<User> getAll(){
        return repository.findAll();
    }

    public void updateUser(User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userInDb= findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userInDb.setEmail(user.getEmail());
        userInDb.setSentimentAnalysis(user.isSentimentAnalysis());
        saveUser(userInDb);
    }

    public boolean saveUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("User"));
            repository.save(user);
            return true;
        }
        catch(Exception e)
        {
            log.info("Failed in SaveUser Method");
            log.error("An error occured while saving the entry: {}", user.getUserName(), e);
            return false;
        }
    }

    public void saveAdminUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("User", "ADMIN"));
        user.setEmail(user.getEmail());
        user.setSentimentAnalysis(user.isSentimentAnalysis());
        repository.save(user);
    }

    public void saveJournalEntryOfUser(User user){
        repository.save(user);
    }

    public User findByUserName(String userName){
        return repository.findByUserName(userName);
    }

    public void deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        repository.deleteByUserName(user);
    }

}
