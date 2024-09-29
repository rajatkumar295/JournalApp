//package com.learn.journalApp.controllers;
//
//import com.learn.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/Journal")
//public class JournalController {
//
//    private Map<Long, JournalEntry> journalEntry=new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getJournalList(){
//        return new ArrayList<>(journalEntry.values());
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalById(@PathVariable long myId){
//        return journalEntry.get(myId);
//    }
//
//    @PostMapping
//    public boolean addJournalEntry(@RequestBody JournalEntry entry ){
//        journalEntry.put(entry.getId(), entry);
//        return true;
//    }
//
//    @PutMapping("id/{myId}")
//    public void updateJournalEntry(@PathVariable long myId, @RequestBody JournalEntry entry) {
//        journalEntry.put(myId, entry);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public void deleteJournalEntry(@PathVariable long myId){
//        journalEntry.remove(myId);
//    }
//
//}
