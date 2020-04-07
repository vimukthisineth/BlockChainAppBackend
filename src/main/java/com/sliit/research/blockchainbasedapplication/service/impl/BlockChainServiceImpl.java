package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.blockChain.Block;
import com.sliit.research.blockchainbasedapplication.blockChain.BlockChain;
import com.sliit.research.blockchainbasedapplication.model.BlockModel;
import com.sliit.research.blockchainbasedapplication.repository.BlockModelRepository;
import com.sliit.research.blockchainbasedapplication.service.BlockChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("blockChainService")
public class BlockChainServiceImpl implements BlockChainService {

    @Autowired
    BlockModelRepository blockModelRepository;

    @Override
    public BlockModel addBlock(Block block) {
        BlockModel blockModel = new BlockModel(block);
        blockModel = blockModelRepository.save(blockModel);
        return blockModel;
    }

    @Override
    public List<Block> getBlockChain() {
        List<Block> blockChain = new ArrayList<>();
        List<BlockModel> blockModels = blockModelRepository.findAll();
        for (BlockModel blockModel : blockModels){
            blockChain.add(new Block(blockModel));
        }
        return blockChain;
    }

    @Override
    public void getBlockChainFromDb() {
        BlockChain blockChain = BlockChain.getInstance();
        if (!blockChain.blockChainRestored){
            List<Block> blocks = getBlockChain();
            blockChain.setBlockChain(new ArrayList<>());
            for (Block block : blocks){
                blockChain.addBlock(block);
            }
            blockChain.blockChainRestored = true;
        }
    }
}
