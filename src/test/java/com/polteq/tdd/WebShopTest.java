package com.polteq.tdd;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Eelco de Jong on 9-3-2017.
 */
public class WebShopTest {

    /**
     * Added unique order ID functionality to mimic creating unique order ids by SQL database
     * This functionality and its test can be removed as soon as a database is implemented
     */
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
        Assert.assertEquals("Alpha", order.getClientName());
    }

    @Test
    public void assertThatTwoOrdersWithSameClientNameCanBeCreated() {
        Order order_1 = new Order();
        Order order_2 = new Order();
        order_1.setClientName("Alpha");
        order_2.setClientName("Alpha");
        Assert.assertNotEquals(order_1.getOrderId(), order_2.getOrderId());
        Assert.assertEquals("Alpha", order_1.getClientName());
        Assert.assertEquals("Alpha", order_2.getClientName());
    }

    @Test
    public void assertThatClientNameCannotBeChangedOnACompletedOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setClientName("Beta");
        Assert.assertEquals("Alpha", order.getClientName());
    }

    @Test
    public void assertThatClientNameCanBeChangedOnANewOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        Assert.assertEquals(OrderStatus.NEW, order.getOrderStatus());
        Assert.assertEquals("Alpha", order.getClientName());
        order.setClientName("Beta");
        Assert.assertEquals("Beta", order.getClientName());
    }

    @Test
    public void assertThatLiquidProductCanBeAddedToOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        LiquidProduct product = new LiquidProduct("Water", "1.50", 1000);
        order.addProduct(product);
        Assert.assertEquals("Water", product.getProductName());
        Assert.assertEquals("1.50", product.getProductPPU());
        Assert.assertEquals(1000, product.getProductAmount());
        Assert.assertEquals("Water", order.getProductByName("Water").get(0));
        Assert.assertEquals("1.50", order.getProductByName("Water").get(1));
        Assert.assertEquals("LIQUID", order.getProductByName("Water").get(2));
        Assert.assertEquals("1000", order.getProductByName("Water").get(3));
        Assert.assertEquals("0.75", order.getProductByName("Water").get(4));
    }

    @Test
    public void assertThatSolidProductCanBeAddedToOrder() {
        Order order = new Order();
        SolidProduct product = new SolidProduct("Bricks", "13.00", 50);
        order.addProduct(product);
        Assert.assertEquals("Bricks",order.getProductByName("Bricks").get(0));
        Assert.assertEquals("13.00", order.getProductByName("Bricks").get(1));
        Assert.assertEquals("SOLID", order.getProductByName("Bricks").get(2));
        Assert.assertEquals("50", order.getProductByName("Bricks").get(3));
        Assert.assertEquals("100", order.getProductByName("Bricks").get(4));
    }

    @Test
    public void assertThatMultipleProductsCanBeAddedToOrder() {
        Order order = new Order();
        order.setClientName("Alpha");
        LiquidProduct product_1 = new LiquidProduct("Water", "1.20", 200);
        SolidProduct product_2 = new SolidProduct("Bricks", "15.10", 1500);
        order.addProduct(product_1);
        order.addProduct(product_2);
        Assert.assertEquals("Water", order.getProductByName("Water").get(0));
        Assert.assertEquals("1.20", order.getProductByName("Water").get(1));
        Assert.assertEquals("LIQUID", order.getProductByName("Water").get(2));
        Assert.assertEquals("200", order.getProductByName("Water").get(3));
        Assert.assertEquals("0.75", order.getProductByName("Water").get(4));
        Assert.assertEquals("Bricks", order.getProductByName("Bricks").get(0));
        Assert.assertEquals("15.10", order.getProductByName("Bricks").get(1));
        Assert.assertEquals("SOLID", order.getProductByName("Bricks").get(2));
        Assert.assertEquals("1500", order.getProductByName("Bricks").get(3));
        Assert.assertEquals("100", order.getProductByName("Bricks").get(4));
    }

    @Test
    public void assertThatFullAmountOfOrderIsCalculatedCorrectly() {
        Order order = new Order();
        order.setClientName("Alpha");
        LiquidProduct product_1 = new LiquidProduct("Water", "1.20", 200);
        SolidProduct product_2 = new SolidProduct("Bricks", "15.10", 1500);
        LiquidProduct product_3 = new LiquidProduct("Oil", "20.00", 500);
        SolidProduct product_4 = new SolidProduct("Rocks", "0.50", 5000);
        order.addProduct(product_1);
        order.addProduct(product_2);
        order.addProduct(product_3);
        order.addProduct(product_4);

        /**
         *  MANUAL CALCULATION OF TOTAL ORDER AMOUNT:
         *  Water   : 1.20 * (200 / 0.75) = 320
         *  Bricks  : 15.10 * (1500 / 100) = 226.50
         *  Oil     : 20.00 * (500 / 0.75) = 13333.33
         *  Rocks   : 0.50 * (5000 / 100) = 25
         *  --------------------------------------------
         *  Total   : 13904.85
         */

        Assert.assertEquals("13904.85", order.calculateOrderAmountForClientName("Alpha").toString());
    }

}
