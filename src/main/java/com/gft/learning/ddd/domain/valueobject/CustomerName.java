package com.gft.learning.ddd.domain.valueobject;

public class CustomerName {
    private final String name;

    public CustomerName(String name) {
        this.name = validateName( name );
    }

    private String validateName( String name ){
        if( name.length() >= 3
                && name.length() <= 50
        ){
            return name;
        }else{
            throw new IllegalArgumentException("Customer name must be between 3 and 50 characters in length");
        }
    }
}
