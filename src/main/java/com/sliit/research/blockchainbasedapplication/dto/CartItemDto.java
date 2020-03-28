package com.sliit.research.blockchainbasedapplication.dto;

import com.sliit.research.blockchainbasedapplication.model.Cart;
import com.sliit.research.blockchainbasedapplication.model.Product;

public class CartItemDto {

    private Long id;
    private Cart cart;
    private Product product;
    private float qty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }
}
