package com.sliit.research.blockchainbasedapplication.repository;

import com.sliit.research.blockchainbasedapplication.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
