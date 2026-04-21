package com.gft.learning.ddd;

import com.gft.learning.ddd.domain.aggregates.Order;
import com.gft.learning.ddd.domain.aggregates.Customer;
import com.gft.learning.ddd.domain.enums.Currency;
import com.gft.learning.ddd.domain.valueobject.*;

import java.math.BigDecimal;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("=========================================================================================");
        System.out.println("|| Hello and welcome to the DDD course!");
        System.out.println("=========================================================================================");

        showOrdersBehavior();

        showCustomersBehavior();
    }

    static void showOrdersBehavior(){
        System.out.println("#########################################################################################");
        System.out.println("# Orders behavior");
        System.out.println("#########################################################################################");
        Address address = new Address(
                "Main Street 1",
                "Madrid",
                "28001",
                "Spain"
        );

        Order order = new Order(
                new OrderId(UUID.randomUUID().toString()),
                address
        );

        order.addItem("product-1", 2, new Price(new BigDecimal("10.00"), Currency.EUR));
        order.addItem("product-2", 1, new Price(new BigDecimal("5.00"), Currency.EUR));

        order.confirm();
        System.out.printf("Expected amount is 25.00, and amount is: %.2f%n",
                order.getTotal().getAmount().floatValue());
        try {
            order.addItem("product-1", 2, new Price(new BigDecimal("10.00"), Currency.EUR));
            // This error shouldn't be shown, we are not able to add item,s on a confirmed order
            System.err.print("We have been able to add a new item, this is not the expected behavior");
        }catch(IllegalStateException ise){
            System.out.printf("We have tried adding a new Order, but we cannot. Error: %s\n", ise.getMessage());
        }
    }

    static void showCustomersBehavior() {
        System.out.println("#########################################################################################");
        System.out.println("# Customers behavior");
        System.out.println("#########################################################################################");

        Customer customer1 = new Customer(
                new CustomerId("1"),
                new CustomerName("John Doe"),
                new EmailAddress("johndoe@mail.com")
        );

        OrderId orderId1 = new OrderId("1");
        OrderId orderId2 = new OrderId("2");
        OrderId orderId3 = new OrderId("3");
        OrderId orderId4 = new OrderId("4");
        OrderId orderId5 = new OrderId("5");
        OrderId orderId6 = new OrderId("6");

        customer1.placeOrder( orderId1 );
        customer1.placeOrder( orderId2 );
        customer1.placeOrder( orderId3 );
        customer1.placeOrder( orderId4 );
        customer1.placeOrder( orderId5 );

        try{
            //Order limit error
            customer1.placeOrder( orderId6 );
        }catch (RuntimeException e){
            System.out.println("ERROR: " + e.getMessage());
        }

        customer1.completeOrder( orderId5 );

        customer1.suspend();
        try{
            //Order under suspension error
            customer1.placeOrder( orderId6);
        }catch (RuntimeException e){
            System.out.println("ERROR: " + e.getMessage());
        }


        customer1.activate();

        try{
                //Duplicated order error
            customer1.placeOrder( orderId1 );
                //Also when it has been completed
            customer1.placeOrder( orderId5 );
        }catch (RuntimeException e){
            System.out.println("ERROR: " + e.getMessage());
        }


    }
}
