package com.gft.learning.ddd.domain.valueobject;

public final class Address {

    //    Task
    //        Create an immutable Address value object with:
    //                street
    //                city
    //                postalCode
    //                country
    //        Rules
    //            No field can be null or blank

    private final String street;
    private final String city;
    private final String postalCode;
    private final String country;

    public Address(String street, String city, String postalCode, String country) {
        this.street = validateNotBlank(street, "street");
        this.city = validateNotBlank(city, "city");
        this.postalCode = validateNotBlank(postalCode, "postalCode");
        this.country = validateNotBlank(country, "country");
    }

    private static String validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be null or blank");
        }
        return value;
    }
}
