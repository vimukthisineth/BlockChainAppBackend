package com.sliit.research.blockchainbasedapplication.repository;

import com.sliit.research.blockchainbasedapplication.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
}
