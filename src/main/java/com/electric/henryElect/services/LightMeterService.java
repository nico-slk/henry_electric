package com.electric.henryElect.services;

import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.repository.LightMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class LightMeterService {

    @Autowired
    private LightMeterRepository lightMeterRepository;

    public LightMeter getLightMeter(Integer id){
        return lightMeterRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<LightMeter> getLightMeters(){
        return lightMeterRepository.findAll();
    }

    public LightMeter addLightMeter(LightMeter medidor){
        return lightMeterRepository.save(medidor);
    }

    public LightMeter editLightMeter(LightMeter medidor) {
        LightMeter lightMeter = lightMeterRepository.findById(medidor.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        LightMeter newLightMeter = new LightMeter();
        newLightMeter.setId(medidor.getId());
        if(medidor.getBrand() != null){
            newLightMeter.setBrand(medidor.getBrand());
        } else {
            newLightMeter.setBrand(lightMeter.getBrand());
        }

        if(medidor.getModel() != null){
            newLightMeter.setModel(medidor.getModel());
        } else {
            newLightMeter.setModel(lightMeter.getModel());
        }

        if(medidor.getInitialMedition() != null){
            newLightMeter.setInitialMedition(medidor.getInitialMedition());
        } else {
            newLightMeter.setInitialMedition(lightMeter.getInitialMedition());
        }

        if(medidor.getFinalMedition() != null){
            newLightMeter.setFinalMedition(medidor.getFinalMedition());
        } else {
            newLightMeter.setFinalMedition(lightMeter.getFinalMedition());
        }

        if(medidor.getTotalConsumption() != null){
            newLightMeter.setTotalConsumption(medidor.getTotalConsumption());
        } else {
            newLightMeter.setTotalConsumption(lightMeter.getTotalConsumption());
        }

        return lightMeterRepository.save(newLightMeter);
    }


    public void deleteLightMeter(Integer id) {
        lightMeterRepository.deleteById(id);
    }
}
