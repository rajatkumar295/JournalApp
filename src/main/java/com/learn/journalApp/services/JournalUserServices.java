package com.learn.journalApp.services;

import com.learn.journalApp.entity.JournalEntry;
import com.learn.journalApp.entity.JournalUser;
import com.learn.journalApp.repository.JournalEntryRepository;
import com.learn.journalApp.repository.JournalUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalUserServices {

    @Autowired
    private JournalUserRepository repository;

    public List<JournalUser> getAll(){
        return repository.findAll();
    }

    public void addJournalUser(JournalUser entry){
        repository.save(entry);
    }

    public JournalUser findByUserName(String userName){
        return repository.findByUserName(userName);
    }

    public void deleteJournalUser(String userName){
        repository.deleteByUserName(userName);
    }

}
