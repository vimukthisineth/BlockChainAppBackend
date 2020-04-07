package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.blockChain.BlockChain;
import com.sliit.research.blockchainbasedapplication.service.BlockChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BlockChainController {

    @Autowired
    BlockChainService blockChainService;

    @GetMapping("/blockChain")
    public BlockChain getBlockChain(){
        return BlockChain.getInstance();
    }

}
