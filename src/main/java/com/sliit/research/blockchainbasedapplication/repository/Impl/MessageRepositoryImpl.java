package com.sliit.research.blockchainbasedapplication.repository.Impl;

import com.sliit.research.blockchainbasedapplication.model.Message;
import com.sliit.research.blockchainbasedapplication.repository.MessageRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class MessageRepositoryImpl implements MessageRepository {
    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public List<Message> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Message> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Message> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Message message) {

    }

    @Override
    public void deleteAll(Iterable<? extends Message> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Message> S save(S s) {
        return null;
    }

    @Override
    public <S extends Message> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Message> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Message> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Message> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Message getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Message> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Message> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Message> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Message> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Message> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Message> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Message sendMessage(Message message) throws ExecutionException, InterruptedException {
        List inputParams = new ArrayList();
        List outputParams = new ArrayList();
        Function function = new Function("sendMessage", inputParams, outputParams);
        String encodedFunction = FunctionEncoder.encode(function);
        Web3j web3j = Web3j.build(new HttpService());
        BigInteger nonce = BigInteger.valueOf(100);
        BigInteger gasprice = BigInteger.valueOf(100);
        BigInteger gaslimit = BigInteger.valueOf(100);

        Transaction transaction = Transaction
                .createFunctionCallTransaction("FROM_ADDRESS",
                        nonce, gasprice, gaslimit, "TO_ADDRESS", encodedFunction);

        EthSendTransaction transactionResponse = web3j.ethSendTransaction(transaction).sendAsync().get();
//        transactionHash = transactionResponse.getTransactionHash();
        return message;
    }
}
