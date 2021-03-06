package com.electric.henryElect.controller;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id){
        return addressService.getAddressByID(id);
    }

    @GetMapping
    public List<Address> getAllAddress(){
        return addressService.getAllAddress();
    }

    @PostMapping
    public String postAddress(@RequestBody Address direccion){
        Address newAddress = addressService.addAddress(direccion);
        return ("Dirección " + newAddress + " ha sido creada.");
    }

    @PutMapping
    public String putAddress(@RequestBody Address direccion){
        Address newAddress = addressService.editAddress(direccion);
        return("Dirección " + newAddress + " ha sido modificada");
    }

    @DeleteMapping("/{id}")
    public String delAddress(@PathVariable Integer id){
        addressService.deleteAddressByID(id);
        return ("Dirección con id " + id + " ha sido eliminada.");
    }



}
