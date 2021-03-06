package com.amazing.common;

import com.amazing.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class CounterService {

    private final MongoOperations mongo;

    @Autowired
    public CounterService(MongoOperations mongo) {
        this.mongo = mongo;
    }

    public long getNextSequence(String collectionName) {
        Counter counter = mongo.findAndModify(query(where("_id").is(collectionName)), new Update().inc("seq", 1),
                options().returnNew(true), Counter.class);
        return counter.getSeq();
    }
}
