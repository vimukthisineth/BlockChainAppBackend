package com.sliit.research.blockchainbasedapplication.repository;

import com.sliit.research.blockchainbasedapplication.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message sendMessage(Message message) throws ExecutionException, InterruptedException;

}
