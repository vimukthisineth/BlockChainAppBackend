package com.sliit.research.blockchainbasedapplication.blockChain;

import com.sliit.research.blockchainbasedapplication.model.Product;

import java.util.Date;

public class BlockData {
    private Long product;
    private Date date;
    private String blockMessage;

    public BlockData() {
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBlockMessage() {
        return blockMessage;
    }

    public void setBlockMessage(String blockMessage) {
        this.blockMessage = blockMessage;
    }
}
