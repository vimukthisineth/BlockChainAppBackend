package com.sliit.research.blockchainbasedapplication.repository;

import com.sliit.research.blockchainbasedapplication.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
