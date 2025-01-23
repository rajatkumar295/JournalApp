package com.learn.journalApp.controllers;

import com.learn.journalApp.entity.JournalEntry;
import com.learn.journalApp.entity.User;
import com.learn.journalApp.services.JournalEntryServices;
import com.learn.journalApp.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices services;

    @Autowired
    private UserServices userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all = services.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> addJournalEntry(@RequestBody JournalEntry entry){
        services.addJournalEntry(entry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getJournalEntriesById(@PathVariable ObjectId id) {
        JournalEntry journalEntry = services.getJournalById(id);
        if (journalEntry!=null) {
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry) {
        JournalEntry updatedEntry = services.updateJournalEntry(id, entry);
        if (updatedEntry!=null) {
            return new ResponseEntity<>(updatedEntry, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id){
        boolean result = services.deleteJournalEntry(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
