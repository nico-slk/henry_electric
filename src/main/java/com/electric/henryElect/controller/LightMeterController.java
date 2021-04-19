package com.electric.henryElect.controller;

import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.services.LightMeterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class LightMeterController {

    @Autowired
    private LightMeterService lightMeterService;

    @GetMapping("invoice/{id}")
    public LightMeter getLightMeter(Integer id){
        return lightMeterService.getLightMeter(id);
    }

//    @PostMapping
//    @Operation(summary = "Dar de alta un medidor")
//    public String addLightMeter(@RequestBody LightMeter lightMeter) {
//        LightMeter createLightMeter = lightMeterService.addLightMeter(lightMeter);
//        return ("Medidor agregado: " + createLightMeter);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Dar de baja un medidor")
//    public String deleteLightMeter(@PathVariable Integer id){
//        lightMeterService.deleteLightMeter(id);
//        return ("Medidor con id " + id + " ha sido borrado.");
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Modificar medidor")
//    public String editLightMeter(@RequestBody LightMeter lightMeter){
//        LightMeter editLightMeter = lightMeterService.editLightMeter(lightMeter);
//        return ("Mediro editado " + editLightMeter);
//    }
//
//    Alta, baja y modificación de domicilios, medidores y clientes.
}
