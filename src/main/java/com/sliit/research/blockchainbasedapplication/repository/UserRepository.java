package com.sliit.research.blockchainbasedapplication.repository;

import com.sliit.research.blockchainbasedapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    List<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = ?1 AND u.token = ?2")
    List<User> findByIdAndToken(long id, String token);

    @Query("SELECT u FROM User u WHERE u.token = ?1")
    List<User> findByToken(String token);
}
