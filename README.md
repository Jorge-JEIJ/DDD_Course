# DDD_Course
Domain Driven Design course from 04/09/2026

# Domain driven Design
Repo URL: https://github.com/AlbertoGRGFT/20260409_DDD_Course

---
## Exercise 1: Add value object Address
    Task
        Create an immutable Address value object with:
            street
            city
            postalCode
            country
        Rules
            No field can be null or blank

## Exercise 2: Protect Invariants on Aggregates
    Task
        Modify Order so that:
            You cannot add items after order is confirmed
            Add method confirm()

## Exercise 3: Introduce Customers with more realistic behavior.
### Introduction
    Domain Story
        Customers can place orders
        Customers have a status
        Customers have limits
        Customers reference orders by ID only
        Customers enforce their own rules, not Order’s rules

    Business Rules (VERY IMPORTANT)
        Identity & Creation
            Customer must have:
                Valid ID
                Valid name
                Valid email
            Customer starts in ACTIVE state
        Order Placement Rules
            Customer can place max 5 active orders
            Customer cannot place duplicate orders
            Customer cannot place orders if SUSPENDED
            Only OrderId is stored (no Order object!)
        Order Lifecycle Tracking 
            Customer tracks:
                Active orders
                Completed orders
            Completed orders cannot be re‑activated
            Encapsulation Rules
                Internal collections must not be modifiable
                No setters
                All rules enforced inside the aggregate

### Task Description
* Starter Code

```java
    public class Customer {
        // TODO
    }
```

* Steps


	Step 1 – Create Value Objects
		CustomerId
			Non‑null, non‑blank
		CustomerName
			Min length: 3
			Max length: 50
		EmailAddress
			Must contain @
			Must contain .
	Step 2 – Create CustomerStatus Enum
			public enum CustomerStatus {
				ACTIVE,
				SUSPENDED
			}
	Step 3 – Create Customer Aggregate Root
		Fields:
			CustomerId id;
			CustomerName name;
			EmailAddress email;
			CustomerStatus status;
			List<OrderId> activeOrders;
			List<OrderId> completedOrders
 
		Behavior to Implement
			void placeOrder(OrderId orderId);
			void completeOrder(OrderId orderId);
			void suspend();
			void activate();
	Step 4 – Update Main, showCustomersBehavior() method
		Create a customer
		Create multiple orders
		Enforce order limits
		Demonstrate suspension behavior (and how place order fails after customer is suspended.