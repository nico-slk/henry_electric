package com.electric.henryElect.services;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.repository.AddressRepository;
import com.electric.henryElect.repository.LightMeterRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class LightMeterService {

    private LightMeterRepository lightMeterRepository;
    private InvoiceService invoiceService;
    private AddressRepository addressRepository;

    @Autowired
    public LightMeterService(LightMeterRepository lightMeterRepository, InvoiceService invoiceService, AddressRepository addressRepository) {
        this.lightMeterRepository = lightMeterRepository;
        this.invoiceService = invoiceService;
        this.addressRepository = addressRepository;
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

//        if(medidor.getAddressid() != null){
//            newLightMeter.setAddressid(medidor.getAddressid());
//        } else {
//            newLightMeter.setAddressid(lightMeter.getAddressid());
//        }

        return lightMeterRepository.save(newLightMeter);
    }


    public void deleteLightMeter(Integer id) {
        lightMeterRepository.deleteById(id);
    }

    @CircuitBreaker(name = "getConsumption",fallbackMethod = "fallback")
    @Scheduled(fixedRate = 300000)
    public void getConsumption(){

        Double consumo = Math.random();
        Double acc = Math.random();
        consumo += acc;
        Integer tarifa = 1;
        LocalDate ahora = LocalDate.now();
        LocalDateTime initialMeditionDate = ahora.atStartOfDay();

        Invoice newInvoice = new Invoice();

        newInvoice.setTimeInitialMedition(initialMeditionDate);
        newInvoice.setTimeFinalMedition(ahora);
        newInvoice.setTotal(consumo * tarifa);

        invoiceService.editInvoice(newInvoice);
        System.out.println(newInvoice + " NEWINVOICE");

    }
    private void fallback(final Throwable t) throws IOException, InterruptedException{
//        log.error(t.getStackTrace().toString());
        System.out.println("NO SOS VOS, SOY YO");
    }
}
