package com.sliit.research.blockchainbasedapplication.blockChain;

import java.util.ArrayList;

public class BlockChain {
    private static BlockChain ourInstance = new BlockChain(new Block("This is genesis block", "0"));

    public static BlockChain getInstance() {
        return ourInstance;
    }

    private ArrayList<Block> blockChain = new ArrayList<Block>();

    private BlockChain(Block genesisBlock) {
        blockChain.add(genesisBlock);
    }

    public void addBlock(Block block){
        blockChain.add(block);
    }

    public ArrayList<Block> getBlockChain() {
        return blockChain;
    }

    public int getBlockChainSize(){
        return blockChain.size();
    }
}
