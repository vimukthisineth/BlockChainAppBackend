package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.dto.CheckoutDto;
import com.sliit.research.blockchainbasedapplication.model.Purchase;
import com.sliit.research.blockchainbasedapplication.model.User;

import java.util.List;

public interface PurchaseService {
    List<Purchase> newPurchase(CheckoutDto checkoutDto);
}
