package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.blockChain.Block;
import com.sliit.research.blockchainbasedapplication.model.BlockModel;

import java.util.List;

public interface BlockChainService {
    BlockModel addBlock(Block block);
    List<Block> getBlockChain();
    void getBlockChainFromDb();
}
