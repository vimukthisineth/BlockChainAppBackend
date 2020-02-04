package com.sliit.research.blockchainbasedapplication.web3j;

import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

@Component
public class Web3JConfig {

    Web3j web3j = Web3j.build(new HttpService());
    Web3j myEtherWallet = Web3j.build(new HttpService("https://api.myetherapi.com/eth"));

    public EthBlockNumber getBlockNumber() throws ExecutionException, InterruptedException {
        EthBlockNumber result = new EthBlockNumber();
        result = this.web3j.ethBlockNumber()
                .sendAsync()
                .get();
        return result;
    }

    public EthAccounts getEthAccounts() throws ExecutionException, InterruptedException {
        EthAccounts result = new EthAccounts();
        result = this.web3j.ethAccounts()
                .sendAsync()
                .get();
        return result;
    }

    public EthGetTransactionCount getTransactionCount() throws ExecutionException, InterruptedException {
        EthGetTransactionCount result = new EthGetTransactionCount();
        result = this.web3j.ethGetTransactionCount("https://api.myetherapi.com/eth",
                DefaultBlockParameter.valueOf("latest"))
                .sendAsync()
                .get();
        return result;
    }

    public EthGetBalance getEthBalance() throws ExecutionException, InterruptedException {
        EthGetBalance result = new EthGetBalance();
        this.web3j.ethGetBalance("https://api.myetherapi.com/eth",
                DefaultBlockParameter.valueOf("latest"))
                .sendAsync()
                .get();
        return result;
    }

}
