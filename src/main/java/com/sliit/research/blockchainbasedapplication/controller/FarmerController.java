package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.exception.ResourceNotFoundException;
import com.sliit.research.blockchainbasedapplication.model.Farmer;
import com.sliit.research.blockchainbasedapplication.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FarmerController {

    @Autowired
    FarmerRepository farmerRepository;

    @GetMapping("/farmers")
    public List<Farmer> getAllFarmers(){
        return farmerRepository.findAll();
    }

    @GetMapping("/farmers/{id}")
    public Farmer getFarmerById(@PathVariable(value = "id") Long farmerId){
        return farmerRepository.findById(farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Farmer", "id", farmerId));
    }

    @PostMapping("/farmers")
    public Farmer createFarmer(HttpServletRequest request, @Valid @RequestBody Farmer farmer){
        System.out.println(request.getHeader("Authorization"));
        return farmerRepository.save(farmer);
    }

}
