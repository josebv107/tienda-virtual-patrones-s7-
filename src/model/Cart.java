package model;

import strategy.NoDiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;
    private DiscountStrategy discountStrategy;

    public Cart() {
        this.products = new ArrayList<>();
        this.discountStrategy = new NoDiscountStrategy();
    }


}
