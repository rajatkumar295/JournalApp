package com.learn.journalApp.controllers;

import com.learn.journalApp.entity.JournalUser;
import com.learn.journalApp.services.JournalUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class JournalUserController {

    @Autowired
    private JournalUserServices services;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalUser> all = services.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addJournalUser(@RequestBody JournalUser entry){
       try{
        services.addJournalUser(entry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("userName/{userName}")
    public ResponseEntity<?> getJournalById(@PathVariable String userName){
        JournalUser getById=  services.findByUserName(userName);
        if(getById!=null){
            return new ResponseEntity<>(getById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("userName/{userName}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable String userName, @RequestBody JournalUser entry){
        JournalUser oldEntry= services.findByUserName(userName);
        if(oldEntry!=null) {
            oldEntry.setUserName(!entry.getUserName().isEmpty() ? entry.getUserName() : oldEntry.getUserName());
            oldEntry.setPassword(!entry.getPassword().isEmpty() ? entry.getPassword() : oldEntry.getPassword());
            services.addJournalUser(oldEntry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalUser(@PathVariable String userName){
        services.deleteJournalUser(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
