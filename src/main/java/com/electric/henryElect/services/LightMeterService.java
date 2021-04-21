package com.electric.henryElect.services;

import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.repository.LightMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LightMeterService {

    private LightMeterRepository lightMeterRepository;
    private InvoiceService invoiceService;

    @Autowired
    public LightMeterService(LightMeterRepository lightMeterRepository, InvoiceService invoiceService) {
        this.lightMeterRepository = lightMeterRepository;
        this.invoiceService = invoiceService;
    }

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

        if(medidor.getAddress() != null){
            newLightMeter.setAddress(medidor.getAddress());
        } else {
            newLightMeter.setAddress(lightMeter.getAddress());
        }

        return lightMeterRepository.save(newLightMeter);
    }


    public void deleteLightMeter(Integer id) {
        lightMeterRepository.deleteById(id);
    }

//    @Scheduled(fixedRate = 10000)
//    public void getConsumption(){
//
//        Double consumo = Math.random();
//        Double acc = Math.random();
//        consumo += acc;
//        Integer tarifa = 1;
//        LocalDate ahora = LocalDate.now();
//        LocalDateTime initialMeditionDate = ahora.atStartOfDay();
//
//        Invoice newInvoice = new Invoice();
//
//        newInvoice.setTimeInitialMedition(initialMeditionDate);
//        newInvoice.setTimeFinalMedition(ahora);
//        newInvoice.setTotal(consumo * tarifa);
//        newInvoice.setId(1);
//
//        invoiceService.editInvoice(newInvoice);
//        System.out.println(newInvoice + " NEWINVOICE");
//
//    }
}
