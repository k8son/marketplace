package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;
import com.silverbars.persistence.InMemoryOrderRepository;
import com.silverbars.persistence.OrderRepository;

/**
 * Quick app to show the functionality of the OrderBoard.
 */
public class App {

    public static void main(String[] args){
        OrderRepository orderRepository = new InMemoryOrderRepository();
        OrderValidationHelper validationHelper = new OrderValidationHelper();
        OrderSummaryBuilder orderSummaryBuilder = new CombinedOrderSummaryBuilder(orderRepository);
        Display display = new ConsoleDisplay();
        Order.OrderBuilder orderBuilder = new Order.OrderBuilder();

        OrderBoard orderBoard = new OrderBoard(orderRepository, validationHelper, orderSummaryBuilder, display);
        orderBoard.registerOrder(orderBuilder.userid("km").orderType(OrderType.BUY).quantity(1d).unitPrice(1.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("qw").orderType(OrderType.BUY).quantity(3d).unitPrice(1.5d).build());
        orderBoard.registerOrder(orderBuilder.userid("ww").orderType(OrderType.BUY).quantity(3d).unitPrice(2.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("xx").orderType(OrderType.BUY).quantity(4d).unitPrice(1.5d).build());
        orderBoard.registerOrder(orderBuilder.userid("cc").orderType(OrderType.BUY).quantity(5d).unitPrice(3.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("km").orderType(OrderType.BUY).quantity(5d).unitPrice(4.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("ds").orderType(OrderType.BUY).quantity(1d).unitPrice(1.5d).build());
        orderBoard.registerOrder(orderBuilder.userid("as").orderType(OrderType.BUY).quantity(2d).unitPrice(1.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("km").orderType(OrderType.BUY).quantity(3d).unitPrice(3.1d).build());

        orderBoard.registerOrder(orderBuilder.userid("km").orderType(OrderType.SELL).quantity(1d).unitPrice(1.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("qw").orderType(OrderType.SELL).quantity(3d).unitPrice(1.5d).build());
        orderBoard.registerOrder(orderBuilder.userid("ww").orderType(OrderType.SELL).quantity(3d).unitPrice(2.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("xx").orderType(OrderType.SELL).quantity(4d).unitPrice(1.5d).build());
        orderBoard.registerOrder(orderBuilder.userid("cc").orderType(OrderType.SELL).quantity(5d).unitPrice(3.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("km").orderType(OrderType.SELL).quantity(5d).unitPrice(4.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("ds").orderType(OrderType.SELL).quantity(1d).unitPrice(1.5d).build());
        orderBoard.registerOrder(orderBuilder.userid("as").orderType(OrderType.SELL).quantity(2d).unitPrice(1.1d).build());
        orderBoard.registerOrder(orderBuilder.userid("km").orderType(OrderType.SELL).quantity(3d).unitPrice(3.1d).build());

        orderBoard.displayOrderBoard();

    }
}
