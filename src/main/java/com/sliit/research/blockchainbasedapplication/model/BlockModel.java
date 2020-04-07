package com.sliit.research.blockchainbasedapplication.model;

import com.sliit.research.blockchainbasedapplication.blockChain.Block;

import javax.persistence.*;

@Entity
@Table(name = "block")
public class BlockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public BlockModel() {
    }

    public BlockModel(Block block) {
        this.hash = block.hash;
        this.previousHash = block.previousHash;
        this.data = block.getData();
        this.timeStamp = block.getTimeStamp();
        this.nonce = block.getNonce();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}
