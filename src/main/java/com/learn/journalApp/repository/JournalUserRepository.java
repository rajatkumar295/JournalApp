package com.learn.journalApp.repository;

import com.learn.journalApp.entity.JournalUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalUserRepository extends MongoRepository<JournalUser, ObjectId> {

    JournalUser findByUserName(String userName);

    void deleteByUserName(String userName);

}
