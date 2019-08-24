package com.example.mongo.demo.subtype.model;

public class InPersonContactInfo extends ContactInfo {
    private String venue;

    public InPersonContactInfo() {
        super(ContactMode.INPERSON);
    }

    public InPersonContactInfo(String venue) {
        this();
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "InPersonContactInfo{" +
                "venue='" + venue + '\'' +
                '}';
    }
}
