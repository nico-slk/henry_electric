package com.electric.henryElect.controller;

import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.services.LightMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lightmeter")
public class LightMeterController {

    @Autowired
    private LightMeterService lightMeterService;

    @GetMapping("/{id}")
    public LightMeter getLightMeterByID(@RequestParam Integer id){
        return lightMeterService.getLightMeter(id);
    }

    @GetMapping
    public List<LightMeter> getAllLightMeter(){
        return lightMeterService.getLightMeters();
    }

    @PostMapping
    public String addLightMeter(@RequestBody LightMeter medidor){
        LightMeter createLightMeter = lightMeterService.addLightMeter(medidor);
        return ("Medidor " + createLightMeter + " ha sido creado.");
    }

    @PutMapping
    public String editLightMeter(@RequestBody LightMeter medidor){
        LightMeter modifyLightMeter = lightMeterService.editLightMeter(medidor);
        return ("Medidor " + modifyLightMeter + " ha sido modificado.");
    }

}
