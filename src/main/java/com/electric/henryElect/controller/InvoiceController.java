package com.electric.henryElect.controller;

import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}") //Consulta de facturas
    public Invoice getInvoiceByID(Integer id){
        return invoiceService.getInvoiceByID(id);
    }

//    @GetMapping("/{id}/consume")
//    public Double rangeConsume(LocalDate initialDate, LocalDate finalDate, Double rate ){
//        Double consume = invoiceService.getConsume(initialDate, finalDate, rate);
//        return consume;
//    }

}
