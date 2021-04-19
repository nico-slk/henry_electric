package com.electric.henryElect.services;

import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.repository.LightMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class LightMeterService {

    @Autowired
    private LightMeterRepository lightMeterRepository;

    public LightMeter getLightMeter(Integer id){
        return lightMeterRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public LightMeter addLightMeter(LightMeter medidor){
        return lightMeterRepository.save(medidor);
    }

    public LightMeter editLightMeter(LightMeter medidor) {

    }
}
