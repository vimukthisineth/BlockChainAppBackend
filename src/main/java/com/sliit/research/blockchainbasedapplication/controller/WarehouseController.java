package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.model.Warehouse;
import com.sliit.research.blockchainbasedapplication.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @PostMapping("/warehouse")
    public Warehouse create(@Valid @RequestBody Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    @GetMapping("/warehouse")
    public List<Warehouse> getAll(){
        return warehouseRepository.findAll();
    }

    @GetMapping("/warehouse/{id}")
    public Warehouse findById(@PathVariable(value = "id") Long id){
        return warehouseRepository.getOne(id);
    }

}
