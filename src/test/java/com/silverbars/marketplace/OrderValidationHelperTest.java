package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.fail;

public class OrderValidationHelperTest{
    private OrderValidationHelper orderValidationHelper;

    @Before
    public void setup(){
        orderValidationHelper = new OrderValidationHelper();
    }

    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationAllAttributesNull(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(), null, null, null, null);
        orderValidationHelper.validate(order);
        fail("Order with null attributes has been accepted");
    }

    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationUserIdEmptyAndOtherAttributesNull(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(), "", null, null, null);
        orderValidationHelper.validate(order);
        fail("Order with null attributes has been accepted");
    }

    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationIfOnlyUnitPriceProvided(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(), "", null, null, 1.1);
        orderValidationHelper.validate(order);
        fail("Order with null attributes has been accepted");
    }

    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationIfOnlyUnitPriceAndQuantityProvided(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(), "", null, 1.0, 1.1);
        orderValidationHelper.validate(order);
        fail("Order with null attributes has been accepted");
    }


    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationIfUserIdAndOrderTypeAreNull(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(), null, null, 1.0, 3.0);
        orderValidationHelper.validate(order);
        fail("Null userid and order type has been accepted");
    }

    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationIfUserIdIsNull(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(),null, OrderType.BUY, 1.0, 3.0);
        orderValidationHelper.validate(order);
        fail("Null userid has been accepted");
    }

    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationIfUserIdIsBlankAndOrderTypeIsNull(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(),null, null, 1.0, 3.0);
        orderValidationHelper.validate(order);
        fail("Blank userid and null order type has been accepted");
    }

    @Test(expected = MarketPlaceException.class)
    public void shouldFailValidationIfUserIdIsEmpty(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(),"", OrderType.BUY, 1.0, 3.0);
        orderValidationHelper.validate(order);
        fail("Empty userid has been accepted");
    }

    @Test
    public void shouldPassValidationIfUserAndOrderTypeProvided(){
        // Don't use the builder here as its not the builder thats under test
        Order order = new Order(UUID.randomUUID(),"kmiah", OrderType.BUY, 1.0, 3.0);
        orderValidationHelper.validate(order);
    }
}
