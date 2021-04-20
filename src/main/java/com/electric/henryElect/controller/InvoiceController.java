package com.electric.henryElect.controller;

import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable Integer id){
        return invoiceService.getInvoiceByID(id);
    }

    @GetMapping
    public List<Invoice> getInvoice(){
        return invoiceService.getAllInvoices();
    }

    @PostMapping
    public String postInvoice(@RequestBody Invoice factura){
        Invoice invoice = invoiceService.createInvoice(factura);
        return ("Factura " + invoice + " ha sido creada");
    }

    @PutMapping
    public String putInvoice(@RequestBody Invoice factura){
        Invoice invoice = invoiceService.editInvoice(factura);
        return ("Factura " + invoice + " ha sido modificada");
    }

    @DeleteMapping("/{id}")
    public void delInvoice(@PathVariable Integer id){
        invoiceService.deleteInvoice(id);
    }

}
