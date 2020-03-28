package com.sliit.research.blockchainbasedapplication.repository;

import com.sliit.research.blockchainbasedapplication.model.Cart;
import com.sliit.research.blockchainbasedapplication.model.CartItem;
import com.sliit.research.blockchainbasedapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);
}
