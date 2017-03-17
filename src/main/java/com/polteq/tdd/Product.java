package com.polteq.tdd;

import java.math.BigDecimal;

/**
 * Created by jongd on 16-3-2017.
 */
public class Product {

    public String productName;
    public BigDecimal productPricePerUnit;
    public ProductType productType;

    Product(String name, String ppu, ProductType type) {
        productName = name;
        productPricePerUnit = getProductPricePerUnit(ppu);
        productType = type;
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
}
