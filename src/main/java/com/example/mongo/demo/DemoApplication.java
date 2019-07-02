package com.example.mongo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        MongoOperations mongoOperations = context.getBean(MongoOperations.class);

        mongoOperations.dropCollection(MySequence.class);
        for (int i = 0; i < 10; i++) {
            System.out.println(nextValue(mongoOperations));
        }
    }

    private static long nextValue(MongoOperations mongoOperations) {
        MySequence counter = mongoOperations.findAndModify(query(where("_id").is("20190701")),
                new Update().inc("currentValue", 1), options().returnNew(true).upsert(true),
                MySequence.class);
        return counter.getCurrentValue();
    }
}
