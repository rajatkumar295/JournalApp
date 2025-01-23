package com.learn.journalApp.repository;

import com.learn.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;
import java.util.Queue;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query=new Query();
        Criteria criteria=new Criteria();
//        query.addCriteria(Criteria.where("userName").is("Rajat"));
//        query.addCriteria(Criteria.where("email").regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"));
//        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        query.addCriteria(criteria.andOperator(Criteria.where("email").regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"),
                Criteria.where("sentimentAnalysis").is(true)));
        List<User> users = mongoTemplate.find(query, User.class);
        if(users.size()<1){
            return null;
        }
        return users;
    }
}
