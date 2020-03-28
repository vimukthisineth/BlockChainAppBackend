package com.sliit.research.blockchainbasedapplication.repository;

import com.sliit.research.blockchainbasedapplication.model.Cart;
import com.sliit.research.blockchainbasedapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

}
