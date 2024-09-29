package com.learn.journalApp.services;

import com.learn.journalApp.entity.JournalEntry;
import com.learn.journalApp.entity.JournalUser;
import com.learn.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository repository;

    @Autowired
    private JournalUserServices userService;

    public List<JournalEntry> getAll(){
        return repository.findAll();
    }

    @Transactional
    public void addJournalEntry(JournalEntry entry, String userName){
        JournalUser user= userService.findByUserName(userName);
        entry.setDate(LocalDateTime.now());
        JournalEntry save = repository.save(entry);
        user.getJournalEntry().add(save);
        userService.addJournalUser(user);
    }

    public void updateJournalEntry(JournalEntry entry){
        repository.save(entry);
    }

    public Optional<JournalEntry> getJournalById(ObjectId id){
        return repository.findById(id);
    }

    public void deleteJournalEntry(ObjectId id, String userName){

        JournalUser user=userService.findByUserName(userName);
        user.getJournalEntry().removeIf(x -> x.getId().equals(id));
        userService.addJournalUser(user);
        repository.deleteById(id);
    }

}
