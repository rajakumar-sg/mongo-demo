package com.example.mongo.demo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoJavaClientDemo {
    public static void main(String[] args) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        try (MongoClient mongoClient =
                     new MongoClient(
                             "localhost",
                             MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build()
                     )
        ) {
            MongoDatabase jsonExample = mongoClient.getDatabase("JsonExample");

            MongoCollection<JsonDoc> jsonDoc = jsonExample.getCollection("JsonDoc", JsonDoc.class);
            JsonDoc jsonDoc1 = new JsonDoc(
                    "Doc1",
                    "something",
                    Arrays.asList(
                            new Contact("email", "somewhere@nowhere.com")
                    )
            );

            jsonDoc.insertOne(jsonDoc1);


            for (JsonDoc doc : jsonDoc.find()) {
                System.out.println(doc);
            }
        }
    }

    public static class JsonDoc {
        private String name;
        private String something;
        private List<Contact> contactList;

        public JsonDoc() {
        }

        public JsonDoc(String name, String something, List<Contact> contactList) {
            this.name = name;
            this.something = something;
            this.contactList = contactList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSomething() {
            return something;
        }

        public void setSomething(String something) {
            this.something = something;
        }

        public List<Contact> getContactList() {
            return contactList;
        }

        public void setContactList(List<Contact> contactList) {
            this.contactList = contactList;
        }
    }

    public static class Contact {
        private String contactType;
        private String contact;

        public Contact() {
        }

        public Contact(String contactType, String contact) {
            this.contactType = contactType;
            this.contact = contact;
        }

        public String getContactType() {
            return contactType;
        }

        public void setContactType(String contactType) {
            this.contactType = contactType;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }
}
