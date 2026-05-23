package model;

import strategy.DiscountStrategy;
import strategy.NoDiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    //Por defecto no aplica ningún descuento, eso se puede cambiar con el set en ejecución
    private DiscountStrategy discountStrategy;

    public Cart() {
        this.products = new ArrayList<>();
        this.discountStrategy = new NoDiscountStrategy();
    }

    //Agregar producto al carrito de compras
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Producto agregado: " + product);
    }

    //Permite cambiar la estrategia de descuento en tiempo de ejecución
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    //Calcula el subtotal segun el precio y cantidad del producto
    public double calculateSubtotal() {
        double subtotal = 0;
        for (Product product : products) {
            subtotal += product.getPrice() *  product.getQuantity();
        }
        return subtotal;
    }

    //Calcula el total aplicando la estrategia de descuento activa sobre el subtotal
    public double calculateTotal() {
        double subtotal = calculateSubtotal();
        return discountStrategy.applyDiscount(subtotal);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void showCart() {
        System.out.println("****** CARRITO DE COMPRAS ******");
        for (Product product : products) {
            System.out.println("  " + product);
        }
        System.out.println("Subtotal: S/ " + calculateSubtotal());
        System.out.println("Total: S/ " + calculateTotal());
    }
}
