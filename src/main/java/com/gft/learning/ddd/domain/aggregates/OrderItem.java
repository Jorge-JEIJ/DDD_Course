package com.gft.learning.ddd.domain.aggregates;

import com.gft.learning.ddd.domain.enums.Currency;
import com.gft.learning.ddd.domain.valueobject.Price;

import java.math.BigDecimal;

class OrderItem {

    private final String productId;
    private final int quantity;
    private final Price price;

    OrderItem(String productId, int quantity, Price price) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    Price getTotalPrice() {
        return new Price(
                price.getAmount().multiply(BigDecimal.valueOf(quantity)),
                Currency.EUR
        );
    }
}
