package com.learn.journalApp.services;

import com.learn.journalApp.entity.JournalEntry;
import com.learn.journalApp.entity.User;
import com.learn.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository repository;

    @Autowired
    private UserServices userService;

    public List<JournalEntry> getAll(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUserName(authentication.getName());
        return user.getJournalEntry();
    }

    @Transactional
    public void addJournalEntry(JournalEntry entry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUserName(authentication.getName());
        entry.setDate(LocalDateTime.now());
        JournalEntry save = repository.save(entry);
        user.getJournalEntry().add(save);
        userService.saveJournalEntryOfUser(user);
    }

    @Transactional
    public JournalEntry updateJournalEntry(Object id, JournalEntry entry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUserName(authentication.getName());
        List<JournalEntry> collect = user.getJournalEntry().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            collect.get(0).setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : collect.get(0).getTitle());
            collect.get(0).setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : collect.get(0).getContent());
            collect.get(0).setSentiment(entry.getSentiment()!=null ? entry.getSentiment() : collect.get(0).getSentiment());
            repository.save(collect.get(0));
            return collect.get(0);
        }
        return null;
    }

    public JournalEntry getJournalById(ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUserName(authentication.getName());
        List<JournalEntry> collect = user.getJournalEntry().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            return collect.get(0);
        }
        return null;
    }

    public Boolean deleteJournalEntry(ObjectId id) {
        boolean result =false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= userService.findByUserName(authentication.getName());
        result = user.getJournalEntry().removeIf(x -> x.getId().equals(id));
        if(result){
            userService.saveUser(user);
            repository.deleteById(id);
            return result;
        }
        return result;
    }

}
