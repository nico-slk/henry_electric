package com.electric.henryElect.services;

import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice getInvoiceByID(Integer id){
        return invoiceRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

//    public Double getConsumePerMinutes(LocalDate initialDate, LocalDate finalDate){
//        Double dif = finalDate - initialDate;
//    }
//
//    public Double getConsume(LocalDate initialDate, LocalDate finalDate, Double rate){
//        Double consume = invoiceRepository.getConsumePerMinutes(initialDate, finalDate);
//        return rate * consume;
//    }
}
