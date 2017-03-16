package com.polteq.tdd;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jongd on 9-3-2017.
 */
public class WebShopTest {

    @Test
    public void assertThatUniqueOrderCanBeCreated() {
        Order order_1 = new Order();
        Order order_2 = new Order();
        Assert.assertNotEquals(order_1.getOrderId(), order_2.getOrderId());
    }

    @Test
    public void assertThatOrderCanBeCreatedByClientName() {
        Order order = new Order();
        order.setClientName("Alpha");
        Assert.assertEquals(order.getClientName(), "Alpha");
    }

    @Test
    public void assertThatTwoOrdersWithSameClientNameCanBeCreated() {
        Order order_1 = new Order();
        Order order_2 = new Order();
        order_1.setClientName("Alpha");
        order_2.setClientName("Alpha");
        Assert.assertNotEquals(order_1.getOrderId(), order_2.getOrderId());
        Assert.assertEquals(order_1.getClientName(), "Alpha");
        Assert.assertEquals(order_2.getClientName(), "Alpha");
    }

    @Test
    public void assertThatClientNameCannotBeChangedOnACompletedOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setClientName("Beta");
        Assert.assertEquals(order.getClientName(),"Alpha");
    }

    @Test
    public void assertThatClientNameCanBeChangedOnANewOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        Assert.assertEquals(order.getOrderStatus(), OrderStatus.NEW);
        Assert.assertEquals(order.getClientName(), "Alpha");
        order.setClientName("Beta");
        Assert.assertEquals(order.getClientName(),"Beta");
    }

    @Test
    public void assertThatProductCanBeAddedToOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        Product product = new Product("Water", "1,50", ProductType.LIQUID);
        order.addProduct(product);
    }


}
