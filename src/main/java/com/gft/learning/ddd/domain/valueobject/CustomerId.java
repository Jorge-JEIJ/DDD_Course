package com.gft.learning.ddd.domain.valueobject;

public class CustomerId {

    private final String id;

    public CustomerId(String id){
        this.id = validateId(id);
    }

    private String validateId(String id) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Customer ID must not be null nor blank");
        } else {
            return id;
        }

    }
}

