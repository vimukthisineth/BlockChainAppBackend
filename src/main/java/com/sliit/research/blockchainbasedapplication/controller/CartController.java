package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.dto.CartItemDto;
import com.sliit.research.blockchainbasedapplication.model.Cart;
import com.sliit.research.blockchainbasedapplication.model.CartItem;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.repository.CartItemRepository;
import com.sliit.research.blockchainbasedapplication.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping("/cartItem")
    public CartItem createCartItem(@Valid @RequestBody CartItemDto cartItemDto){
        CartItem cartItem = new CartItem(cartItemDto);
        Cart cart = new Cart();
        if (cartRepository.findByUser(cartItem.getCart().getUser()).size() > 0){
            cart = cartRepository.findByUser(cartItem.getCart().getUser()).get(0);
        }else {
            cart.setUser(cartItem.getCart().getUser());
            cartRepository.save(cart);
        }
        cartItem.setCart(cart);
        cartItem = cartItemRepository.save(cartItem);
        return cartItem;
    }

    @PostMapping("/cartItemsByUser")
    public List<CartItem> getCartItemsByUser(@Valid @RequestBody User user){
        List<Cart> carts = cartRepository.findByUser(user);
//        return carts.get(0).getCartItems();
        if (carts.size()>0){
            return carts.get(0).getCartItems();
        }else {
            return null;
        }
    }

    @PostMapping("/removeCartItem")
    public int removeCartItem(@Valid @RequestBody CartItem cartItem){
        cartItemRepository.delete(cartItem);
        return 1;
    }

}
