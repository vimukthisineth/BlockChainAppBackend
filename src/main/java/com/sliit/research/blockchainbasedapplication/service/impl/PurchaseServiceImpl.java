package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.constants.ProductType;
import com.sliit.research.blockchainbasedapplication.dto.CheckoutDto;
import com.sliit.research.blockchainbasedapplication.model.Cart;
import com.sliit.research.blockchainbasedapplication.model.CartItem;
import com.sliit.research.blockchainbasedapplication.model.Purchase;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.repository.CartItemRepository;
import com.sliit.research.blockchainbasedapplication.repository.CartRepository;
import com.sliit.research.blockchainbasedapplication.repository.PurchaseRepository;
import com.sliit.research.blockchainbasedapplication.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<Purchase> newPurchase(CheckoutDto checkoutDto) {
        List<Cart> carts = cartRepository.findByUser(checkoutDto.getUser());
        List<Purchase> purchases = new ArrayList<>();
        if (carts.size() > 0){
            Cart cart = carts.get(0);
            List<CartItem> cartItems = cart.getCartItems();
            for (CartItem cartItem : cartItems){
                Double price = cartItem.getProduct().getRetailPrice();
                if (cartItem.getProduct().getProductType() == ProductType.FARMER){
                    price = cartItem.getProduct().getFarmerPrice();
                }
                Purchase purchase = new Purchase(
                        cartItem.getProduct(),
                        new Date(),
                        price,
                        cartItem.getQty(),
                        checkoutDto.getAddress()
                );
                purchase.setUser(checkoutDto.getUser());
                purchase = purchaseRepository.save(purchase);
                purchases.add(purchase);
                cartItemRepository.delete(cartItem);
            }
            return purchases;
        } else {
            return null;
        }
    }
}
