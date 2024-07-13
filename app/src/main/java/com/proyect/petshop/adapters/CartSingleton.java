package com.proyect.petshop.adapters;

import com.proyect.petshop.models.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartSingleton {
    private static CartSingleton instance;
    private Map<Product, Integer> cartItems; // Usar un mapa para almacenar productos y su cantidad
    private int cartItemCount; // Contador para la cantidad total de productos en el carrito

    private CartSingleton() {
        cartItems = new HashMap<>();
        cartItemCount = 0;
    }

    public static synchronized CartSingleton getInstance() {
        if (instance == null) {
            instance = new CartSingleton();
        }
        return instance;
    }

    public void addToCart(Product product) {
        if (cartItems.containsKey(product)) {
            // Si el producto ya está en el carrito, incrementar la cantidad
            int currentQuantity = cartItems.get(product);
            cartItems.put(product, currentQuantity + 1);
        } else {
            // Si es un nuevo producto, agregarlo con cantidad 1
            cartItems.put(product, 1);
        }
        cartItemCount++; // Incrementar el contador total de productos en el carrito
    }

    public void removeFromCart(Product product) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            if (currentQuantity > 1) {
                // Si hay más de una instancia, decrementar la cantidad
                cartItems.put(product, currentQuantity - 1);
            } else {
                // Si solo hay una instancia, eliminar el producto del carrito
                cartItems.remove(product);
            }
            cartItemCount--; // Decrementar el contador total de productos en el carrito
        }
    }

    public List<Product> getCartItems() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                productList.add(product); // Añadir la cantidad correcta al carrito
            }
        }
        return productList;
    }

    public void clearCart() {
        cartItems.clear();
        cartItemCount = 0; // Reiniciar el contador total de productos en el carrito
    }

    public int getCartItemCount() {
        return cartItemCount;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrecio() * quantity; // Precio del producto por la cantidad
        }
        return total;
    }
}
