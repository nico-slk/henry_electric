package com.electric.henryElect.controller;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.services.AddressService;
import com.electric.henryElect.services.LightMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;
    private LightMeterService lightMeterService;

    @Autowired
    public AddressController(AddressService addressService, LightMeterService lightMeterService) {
        this.addressService = addressService;
        this.lightMeterService = lightMeterService;
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id){
        LightMeter lightMeter = lightMeterService.getLightMeter(id);
        return addressService.getAddressByLightMeter(lightMeter);
    }

    @PostMapping
    public String postAddress(@RequestBody Address direccion){
        Address newAddress = addressService.addAddress(direccion);
        return ("Dirección " + newAddress + " ha sido creada.");
    }


    @PutMapping
    public String putAddress(@RequestBody Address direccion){
        Address newAddress = addressService.editAddress(direccion);
        return ("Dirección " + newAddress + " ha sido modificada.");
    }

    @DeleteMapping("/{id}")
    public String delAddress(@PathVariable Integer id){
        LightMeter lightMeter = lightMeterService.getLightMeter(id);
        addressService.deleteAddressByLightMeter(lightMeter);
        return ("Dirección con medidor " + lightMeter + " ha sido eliminada.");
    }

}
