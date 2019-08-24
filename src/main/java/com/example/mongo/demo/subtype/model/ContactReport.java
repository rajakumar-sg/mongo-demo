package com.example.mongo.demo.subtype.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ContactReport {
    @Id
    private String id;
    private String name;

    private ContactInfo contactInfo;

    public ContactReport(String id, String name, ContactInfo contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "ContactReport{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
