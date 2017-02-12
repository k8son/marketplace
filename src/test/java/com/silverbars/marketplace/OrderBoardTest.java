package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;
import com.silverbars.persistence.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderBoardTest {
    private Order.OrderBuilder orderBuilder;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderValidationHelper orderValidatorHelper;
    @Mock
    private OrderSummaryBuilder orderSummaryBuilder;
    @Mock
    private Display display;

    @InjectMocks
    private OrderBoard orderBoard;

    @Before
    public void setup(){
        orderBuilder = new Order.OrderBuilder();
    }

    @Test
    public void shouldRegisterOrder(){
        Order order = orderBuilder.userid("km").orderType(OrderType.BUY).quantity(1d).unitPrice(1d).build();
        orderBoard.registerOrder(order);
        verify(orderValidatorHelper, times(1)).validate(order);
        verify(orderRepository, times(1)).addOrder(order);
    }

    @Test
    public void shouldCancelOrder(){
        UUID id = UUID.randomUUID();
        orderBoard.cancelOrder(id);
        verify(orderRepository, times(1)).removeOrder(id);
    }

    @Test
    public void shouldDisplayOrderBoard(){
        Map<OrderType, List<Order>> summary = createSummary();
        when(orderSummaryBuilder.getOrderSummary()).thenReturn(summary);
        orderBoard.displayOrderBoard();
        verify(orderSummaryBuilder, times(1)).getOrderSummary();
        verify(display, times(1)).displayOrders(summary);
    }

    private Map<OrderType, List<Order>>  createSummary() {
        Map<OrderType, List<Order>> summary = new HashMap<>();
        summary.put(OrderType.BUY, Arrays.asList(
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 1d, 1.1d),
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 1d, 0.5d)));
        summary.put(OrderType.SELL, Arrays.asList(
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 1d, 1.1d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 1d, 0.5d)));
        return summary;
    }

}