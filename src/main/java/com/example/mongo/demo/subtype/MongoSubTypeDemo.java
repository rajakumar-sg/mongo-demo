package com.example.mongo.demo.subtype;

import com.example.mongo.demo.subtype.model.ContactReport;
import com.example.mongo.demo.subtype.model.InPersonContactInfo;
import com.example.mongo.demo.subtype.model.TelephoneContactInfo;
import com.example.mongo.demo.subtype.repo.ContactReportRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongoSubTypeDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MongoSubTypeDemo.class);
        MongoDbFactory mongoDbFactory = context.getBean(MongoDbFactory.class);
        mongoDbFactory.getDb("mongo-demo").drop();
        ContactReportRepository contactRepo = context.getBean(ContactReportRepository.class);



        contactRepo.save(new ContactReport("1", "First Call Report", new TelephoneContactInfo("12345")));
        contactRepo.save(new ContactReport("2", "First Call Report", new InPersonContactInfo("somewhere")));

        contactRepo.findAll()
                .stream()
                .forEach(System.out::println);

    }
}
