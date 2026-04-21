package com.gft.learning.ddd.domain.valueobject;

public class EmailAddress {
    private final String email;

    public EmailAddress(String email) {
        this.email = validateEmail( email );
    }

    private String validateEmail(String email) {
        if( email.contains("@")
                && email.contains(".")
        ){
            return email;
        }else {
            throw new IllegalArgumentException("Email address must contain an '@' and a '.'");
        }
    }
}
