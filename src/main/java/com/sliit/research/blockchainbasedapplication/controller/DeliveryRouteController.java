package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.dto.DeliveryRouteDto;
import com.sliit.research.blockchainbasedapplication.exception.ResourceNotFoundException;
import com.sliit.research.blockchainbasedapplication.model.DeliveryRoute;
import com.sliit.research.blockchainbasedapplication.repository.DeliveryRepository;
import com.sliit.research.blockchainbasedapplication.repository.DeliveryRouteRepository;
import com.sliit.research.blockchainbasedapplication.service.DeliveryRouteService;
import com.sliit.research.blockchainbasedapplication.utils.RouteElement;
import com.sliit.research.blockchainbasedapplication.utils.RoutePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DeliveryRouteController {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryRouteRepository deliveryRouteRepository;

    @Autowired
    DeliveryRouteService deliveryRouteService;

    @PostMapping("/deliveryRoute")
    public RoutePath createDeliveryRoute(@Valid @RequestBody DeliveryRouteDto deliveryRouteDto) throws IOException, JSONException {
        DeliveryRoute deliveryRoute = new DeliveryRoute(deliveryRouteDto.getRemarks());
        DeliveryRoute savedRoute = deliveryRouteRepository.save(deliveryRoute);
        return deliveryRouteService.calculateRoute(deliveryRouteDto);
    }

    @GetMapping("/deliveryRoute/{id}")
    public DeliveryRoute getDeliveryRouteById(@PathVariable(value = "id") Long deliveryRouteId){
        return deliveryRouteRepository.findById(deliveryRouteId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Route", "id", deliveryRouteId));
    }
}
