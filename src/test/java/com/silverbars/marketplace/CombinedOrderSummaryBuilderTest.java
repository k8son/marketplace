package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;
import com.silverbars.persistence.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CombinedOrderSummaryBuilderTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private CombinedOrderSummaryBuilder orderSummaryBuilder;

    @Before
    public void setup(){
        when(orderRepository.getAllOrders()).thenReturn(createOrders());
    }

    @Test
    public void shouldGetOrderSummary(){
        Map<OrderType, List<Order>> summary = orderSummaryBuilder.getOrderSummary();
        List<Order> buys = summary.get(OrderType.BUY);
        List<Order> sells = summary.get(OrderType.SELL);

        assertThat(buys.size(), is(6)); // One less than created, due to the combining of orders
        assertThat(buys.get(0).getUnitPrice(), is(10.0d));
        assertThat(buys.get(1).getUnitPrice(), is(8.7d));
        assertThat(buys.get(2).getUnitPrice(), is(6.3d));
        assertThat(buys.get(2).getQuantity(), is(11.0d)); // Quantity is the result of combining of orders
        assertThat(buys.get(3).getUnitPrice(), is(4.0d));
        assertThat(buys.get(4).getUnitPrice(), is(1.1d));
        assertThat(buys.get(5).getUnitPrice(), is(0.8d));

        assertThat(sells.size(), is(5));
        assertThat(sells.get(0).getUnitPrice(), is(0.5d));
        assertThat(sells.get(1).getUnitPrice(), is(2.0d));
        assertThat(sells.get(1).getQuantity(), is(10.0d));
        assertThat(sells.get(2).getUnitPrice(), is(2.2d));
        assertThat(sells.get(3).getUnitPrice(), is(2.8d));
        assertThat(sells.get(4).getUnitPrice(), is(6.4d));
        verify(orderRepository, times(1)).getAllOrders();
    }

    private List<Order> createOrders(){
        return Arrays.asList(
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 1d, 1.1d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 2d, 2.0d),
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 3d, 0.8d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 1d, 6.4d),
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 4d, 10.0d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 3d, 2.0d),
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 5d, 6.3d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 4d, 2.8d),
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 1d, 4.0d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 5d, 2.0d),
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 2d, 8.7d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 1d, 2.2d),
                new Order(UUID.randomUUID(), "km", OrderType.BUY, 6d, 6.3d),
                new Order(UUID.randomUUID(), "km", OrderType.SELL, 1d, 0.5d));
    }

}