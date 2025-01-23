package com.learn.journalApp.repository;

import com.learn.journalApp.entity.ConfigJournalAppEntity;
import com.learn.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
