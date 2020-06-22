package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.model.DistributorPurchase;
import com.sliit.research.blockchainbasedapplication.repository.DistributorPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DistributorPurchaseController {

    @Autowired
    DistributorPurchaseRepository distributorPurchaseRepository;

    @PostMapping("/distributorPurchase")
    public DistributorPurchase create(@Valid @RequestBody DistributorPurchase distributorPurchase){
        distributorPurchase.setDate(new Date());
        return distributorPurchaseRepository.save(distributorPurchase);
    }

    @GetMapping("/distributorPurchases")
    public List<DistributorPurchase> getAll(){
        return distributorPurchaseRepository.findAll();
    }

}
