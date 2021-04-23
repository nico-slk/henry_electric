package com.electric.henryElect.controller;

import com.electric.henryElect.dtos.InvoiceDTO;
import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    // Consulta de consumo por rango de fechas (el usuario va a ingresar un rango
    // de fechas y quiere saber cuánto consumió en ese periodo en Kwh y dinero)
    @GetMapping("/consumebydate/{userid}")
    public InvoiceDTO getConsumeByDate(@RequestBody LocalDateTime init, @RequestBody LocalDateTime end, @PathVariable Integer userid){
        return invoiceService.getConsumeByDate(init, end, userid);
    }

    // Consulta de mediciones de un domicilio en particular
    @GetMapping("/meditions/{addressid}")
    public List<Double> getAddressMeditions(@PathVariable Integer addressid){
        return invoiceService.getAddressMeditions(addressid);
    }

    @GetMapping("/consume/{userid}")
    public List<Double> getMeditionByDate(@RequestBody LocalDateTime init, @RequestBody LocalDateTime end, @PathVariable Integer userid){
        return invoiceService.getMeditionByDate(init, end, userid);
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

    @PostMapping("/{invoiceid}/client/{clientid}")
    public Invoice addClientToInvoice(@PathVariable Integer invoiceid,@PathVariable Integer clientid){
        return invoiceService.addClientToInvoice(invoiceid, clientid);
    }
}
