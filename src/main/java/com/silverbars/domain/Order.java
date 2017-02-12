package com.silverbars.domain;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static java.lang.String.format;

/**
 * Representation of an order.
 */
public class Order implements Comparable{
    // Ideally we want the order id to be sequential and unique.
    // Typically this would be either set by the DB on insertion or from from some distributed order id generator;
    // A DB or a Order Id generator is assumed to be outside of scope for this exercise and therefore I'm just using
    // a UUID.
    private final UUID id;
    @NotBlank(message = "USERID must be provided")
    private final String userid;
    @NotNull(message = "ORDER TYPE must be provided")
    private final OrderType orderType;
    @NotNull(message = "QUANTITY must be provided")
    private Double quantity;
    @NotNull(message = "UNIT PRICE must be provided")
    private final Double unitPrice;


    public Order(UUID id, String userid, OrderType orderType, Double quantity, Double unitPrice) {
        this.id = id;
        this.userid = userid;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderType = orderType;
    }

    public UUID getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * Combines the quantity of this order with that of another one od matching unit price.
     * @param order
     */
    public void combineWith(Order order){
        quantity = quantity + order.getQuantity();
    }

    @Override
    public String toString(){
        return format("%.2f kg for £ %.2f", quantity, unitPrice);
    }

    @Override
    public int compareTo(Object o) {
        // AC1: Display BUY orders before SELL orders
        // AC2: For SELL orders the orders with lowest prices are displayed first
        // AC3: For BUY orders the orders with highest prices are displayed first

        Order order = (Order)o;
        if (this.orderType.equals(order.orderType)) {
            if (this.orderType.equals(OrderType.BUY)){
                return order.getUnitPrice().compareTo(this.getUnitPrice());
            }
            else{
                return this.getUnitPrice().compareTo(order.getUnitPrice());
            }
        }
        else {
            // Show BUY orders first, therefore order by orderType alphabetically
            return this.getOrderType().compareTo(order.getOrderType());
        }
    }

    public static class OrderBuilder{
        private String userid;
        private Double quantity;
        private Double unitPrice;
        private OrderType orderType;

        public OrderBuilder userid(String userid){
            this.userid = userid;
            return this;
        }

        public OrderBuilder quantity(Double quantity){
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder unitPrice(Double unitPrice){
            this.unitPrice = unitPrice;
            return this;
        }

        public OrderBuilder orderType(OrderType orderType){
            this.orderType = orderType;
            return this;
        }

        public Order build(){
            return new Order(UUID.randomUUID(), userid, orderType, quantity, unitPrice);
        }
    }
}
