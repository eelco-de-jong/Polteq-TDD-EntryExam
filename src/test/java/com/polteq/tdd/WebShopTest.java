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
        Product product = new Product("Water", "1.50", ProductType.LIQUID, 1000);
        order.addProduct(product);
        Assert.assertEquals(product.getProductName(), "Water");
        Assert.assertEquals(product.getProductPPU(), "1.50");
        Assert.assertEquals(product.getProductType(), "LIQUID");
        Assert.assertEquals(order.getProductByName("Water").get(0), "Water");
        Assert.assertEquals(order.getProductByName("Water").get(1), "1.50");
        Assert.assertEquals(order.getProductByName("Water").get(2), "LIQUID");
        Assert.assertEquals(order.getProductByName("Water").get(2), "1000");
    }

    @Test
    public void assertThatMultipleProductsCanBeAddedToOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        Product product_1 = new Product("Water", "1.20", ProductType.LIQUID, 200);
        Product product_2 = new Product("Bricks", "15.10", ProductType.SOLID, 1500);
        order.addProduct(product_1);
        order.addProduct(product_2);
        Assert.assertEquals(order.getProductByName("Water").get(0), "Water");
        Assert.assertEquals(order.getProductByName("Water").get(1), "1.20");
        Assert.assertEquals(order.getProductByName("Water").get(2), "LIQUID");
        Assert.assertEquals(order.getProductByName("Water").get(3), "200");
        Assert.assertEquals(order.getProductByName("Bricks").get(0), "Bricks");
        Assert.assertEquals(order.getProductByName("Bricks").get(1), "15.10");
        Assert.assertEquals(order.getProductByName("Bricks").get(2), "SOLID");
        Assert.assertEquals(order.getProductByName("Bricks").get(2), "1500");
    }

    @Test
    public void assertThatFullAmountOfOrderIsCalculatedCorrectly() {
        Order order = new Order();
        order.setClientName("Alpha");
        Product product_1 = new Product("Water", "1.20", ProductType.LIQUID, 200);
        Product product_2 = new Product("Bricks", "15.10", ProductType.SOLID, 1500);
        order.addProduct(product_1);
        order.addProduct(product_2);
        /**
         *  Calculation:
         *  Water: 1.20 * (200 / 0.75) = 320
         *  Bricks: 15.10 * (1500 / 100) = 226.50
         */
        Assert.assertEquals(order.calculateOrderAmountForClientName("Alpha"), "226,50");
    }


}
