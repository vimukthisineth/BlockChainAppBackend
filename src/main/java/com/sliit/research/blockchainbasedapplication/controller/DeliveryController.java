package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.exception.ResourceNotFoundException;
import com.sliit.research.blockchainbasedapplication.model.Delivery;
import com.sliit.research.blockchainbasedapplication.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DeliveryController {

    @Autowired
    DeliveryRepository deliveryRepository;

    @PostMapping("/delivery")
    public Delivery createDelivery(HttpServletRequest request, @Valid @RequestBody Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    @GetMapping("/delivery/{id}")
    public Delivery getProductById(@PathVariable(value = "id") Long deliveryId){
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", deliveryId));
    }

    @GetMapping("/deliveries")
    public List<Delivery> getAllDeliveries(){
        return deliveryRepository.findAll();
    }

}
