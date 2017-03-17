package com.polteq.tdd;

import java.math.BigDecimal;

/**
 * Created by jongd on 16-3-2017.
 */
public class Product {

    private String productName;
    private BigDecimal productPricePerUnit;
    private ProductType productType;
    private int productAmount;

    Product(String name, String ppu, ProductType type, int amount) {
        productName = name;
        productPricePerUnit = getProductPricePerUnit(ppu);
        productType = type;
        productAmount = amount;
    }

    private BigDecimal getProductPricePerUnit(String ppu) {
        BigDecimal price = new BigDecimal(ppu);
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPPU() {
        String ppu = productPricePerUnit.toString();
        return ppu;
    }

    public String getProductType() {
        String ppt = productType.toString();
        return ppt;
    }

    public int getProductAmount() {
        return productAmount;
    }
}
