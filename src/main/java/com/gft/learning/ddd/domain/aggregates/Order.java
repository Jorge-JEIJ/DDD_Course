package com.gft.learning.ddd.domain.aggregates;
import com.gft.learning.ddd.domain.enums.Currency;
import com.gft.learning.ddd.domain.valueobject.Address;
import com.gft.learning.ddd.domain.valueobject.OrderId;
import com.gft.learning.ddd.domain.valueobject.Price;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final OrderId id;
    private final Address shippingAddress;
    private final List<OrderItem> items = new ArrayList<>();
    private Price totalPrice;
    private boolean confirmed;

    public Order(OrderId id, Address shippingAddress) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.totalPrice = new Price(BigDecimal.ZERO, Currency.EUR);

        this.confirmed = false;
    }

    public void addItem(String productId, int quantity, Price price) {
        if(confirmed){
            throw new IllegalStateException("Order is already confirmed, dumb");
        } else {
            OrderItem item = new OrderItem(productId, quantity, price);
            items.add(item);
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
    }

    public Price getTotal() {
        return totalPrice;
    }

    public void confirm(){
        this.confirmed = true;
    }
}
