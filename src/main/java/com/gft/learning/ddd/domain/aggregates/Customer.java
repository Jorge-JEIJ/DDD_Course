package com.gft.learning.ddd.domain.aggregates;

import com.gft.learning.ddd.domain.enums.CustomerStatus;
import com.gft.learning.ddd.domain.valueobject.CustomerId;
import com.gft.learning.ddd.domain.valueobject.CustomerName;
import com.gft.learning.ddd.domain.valueobject.EmailAddress;
import com.gft.learning.ddd.domain.valueobject.OrderId;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final CustomerId customerId;
    private final CustomerName customerName;
    private final EmailAddress emailAddress;
    private CustomerStatus status;
    private List<OrderId> activeOrders = new ArrayList<>();
    private List<OrderId> completedOrders = new ArrayList<>();

    public Customer(CustomerId customerId, CustomerName customerName, EmailAddress emailAddress) {
        if (customerId == null || customerName == null || emailAddress == null  )
            throw new IllegalArgumentException("Customer values must not be null");

        this.customerId = customerId;
        this.customerName = customerName;
        this.emailAddress = emailAddress;

        this.status = CustomerStatus.ACTIVE;
    }

    public void placeOrder(OrderId orderId){
        if( this.status == CustomerStatus.SUSPENDED )
            throw new IllegalStateException("Customer cannot place orders if SUSPENDED");

        if( this.activeOrders.size() >= 5 )
            throw new IllegalComponentStateException("Customer cannot place more than 5 active orders. Current Nº of orders: " + this.activeOrders.size());

        if( this.activeOrders.contains( orderId ) || this.completedOrders.contains( orderId ) )
            throw new IllegalArgumentException("Customer cannot place duplicate orders");

        this.activeOrders.add( orderId );
    }

    public void completeOrder(OrderId orderId){
        this.activeOrders.remove( orderId );
        this.completedOrders.add( orderId );
    }

    public void suspend(){
        this.status = CustomerStatus.SUSPENDED;
    }

    public void activate(){
        this.status = CustomerStatus.ACTIVE;
    }
}
