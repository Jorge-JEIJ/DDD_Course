package com.gft.learning.ddd.domain.valueobject;

import com.gft.learning.ddd.domain.enums.Currency;

import java.math.BigDecimal;

public class Price {
    private final BigDecimal amount;
    private final Currency currency;

    public Price(BigDecimal amount, Currency currency) {
        if(amount == null ||currency == null){
            throw new IllegalArgumentException("Both amount & currency must bne not null");
        }
        if(amount.compareTo(BigDecimal.ZERO) < 0){
           throw new IllegalArgumentException("Amount must me greater than zero");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public Price add(Price price){
        // Add validations
        //      same currency
        return new Price(this.amount.add(price.amount), this.currency);
    }

    public BigDecimal getAmount(){
        return this.amount;
    }

    public String toString(){
        return String.format("%.2f %s", amount.floatValue(), currency.name());
    }

}
