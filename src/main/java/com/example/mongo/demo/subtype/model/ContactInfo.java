package com.example.mongo.demo.subtype.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "contactType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TelephoneContactInfo.class, name = "TELEPHONE"),
        @JsonSubTypes.Type(value = InPersonContactInfo.class, name = "INPERSON")
})
public abstract class ContactInfo {
    private ContactMode contactMode;

    protected ContactInfo(ContactMode contactMode) {
        this.contactMode = contactMode;
    }

    public ContactMode getContactMode() {
        return contactMode;
    }

    public enum ContactMode {
        TELEPHONE,
        INPERSON
    }
}
