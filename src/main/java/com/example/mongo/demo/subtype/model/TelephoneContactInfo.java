package com.example.mongo.demo.subtype.model;

public class TelephoneContactInfo extends ContactInfo {
    private String telephoneNo;

    public TelephoneContactInfo() {
        super(ContactMode.TELEPHONE);
    }

    public TelephoneContactInfo(String telephoneNo) {
        this();
        this.telephoneNo = telephoneNo;
    }

    @Override
    public String toString() {
        return "TelephoneContactInfo{" +
                "telephoneNo='" + telephoneNo + '\'' +
                '}';
    }
}
