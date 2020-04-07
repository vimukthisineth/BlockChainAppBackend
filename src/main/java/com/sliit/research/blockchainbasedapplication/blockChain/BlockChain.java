package com.sliit.research.blockchainbasedapplication.blockChain;

import java.util.ArrayList;

public class BlockChain {
    private static BlockChain ourInstance = new BlockChain(new Block("This is genesis block", "0"));

    public static BlockChain getInstance() {
        return ourInstance;
    }

    public int difficulty = 5;
    public boolean blockChainRestored = false;

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

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    public void setBlockChain(ArrayList<Block> blockChain) {
        this.blockChain = blockChain;
    }
}
