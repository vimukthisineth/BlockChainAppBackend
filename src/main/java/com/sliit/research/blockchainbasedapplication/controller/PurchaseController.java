package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.dto.DeliveryRouteDto;
import com.sliit.research.blockchainbasedapplication.model.Delivery;
import com.sliit.research.blockchainbasedapplication.model.DeliveryRoute;
import com.sliit.research.blockchainbasedapplication.model.Purchase;
import com.sliit.research.blockchainbasedapplication.repository.PurchaseRepository;
import com.sliit.research.blockchainbasedapplication.service.DeliveryRouteService;
import com.sliit.research.blockchainbasedapplication.utils.RoutePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    DeliveryRouteService deliveryRouteService;

    @PostMapping("/purchases")
    public List<Purchase> getAll(){
        return purchaseRepository.findAll();
    }

    @PostMapping("/customerDeliveryRoute")
    public RoutePath createDeliveryRoute(@Valid @RequestBody List<Purchase> purchases) throws IOException, JSONException {
        DeliveryRouteDto deliveryRouteDto = new DeliveryRouteDto();
        List<Delivery> deliveries = new ArrayList<>();
        for (Purchase purchase : purchases){
            deliveries.add(new Delivery(purchase.getWarehouse().getAddress(), purchase.getAddress()));
        }
        deliveryRouteDto.setDeliveries(deliveries);
        return deliveryRouteService.calculateRoute(deliveryRouteDto);
    }

}
