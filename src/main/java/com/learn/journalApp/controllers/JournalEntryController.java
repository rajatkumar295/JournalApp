package com.learn.journalApp.controllers;

import com.learn.journalApp.entity.JournalEntry;
import com.learn.journalApp.entity.JournalUser;
import com.learn.journalApp.services.JournalEntryServices;
import com.learn.journalApp.services.JournalUserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices services;

    @Autowired
    private JournalUserServices userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all = services.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("userName/{userName}")
    public ResponseEntity<?> addJournalEntry(@RequestBody JournalEntry entry, @PathVariable String userName){
       try{
           services.addJournalEntry(entry, userName);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("userName/{username}")
    public ResponseEntity<?> getJournalEntriesOfUser(@PathVariable String userName){
        JournalUser user=userService.findByUserName(userName);
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{userName}/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry, @PathVariable String userName){
        JournalEntry oldEntry= services.getJournalById(id).orElse(null);
        if(oldEntry!=null) {
            oldEntry.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : oldEntry.getContent());
            services.updateJournalEntry(oldEntry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{userName}/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id, @PathVariable String userName){
        services.deleteJournalEntry(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
