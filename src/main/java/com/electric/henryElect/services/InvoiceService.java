package com.electric.henryElect.services;

import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;


@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice getInvoiceByID(Integer id){
        return invoiceRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice createInvoice(Invoice factura) {
        return invoiceRepository.save(factura);
    }

    public Invoice editInvoice(Invoice factura) {
        Invoice invoice = invoiceRepository.findById(factura.getId())
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Invoice newInvoice = new Invoice();
        newInvoice.setId(factura.getId());
        if(factura.getTimeInitialMedition() != null){
            newInvoice.setTimeInitialMedition(factura.getTimeInitialMedition());
        } else {
            newInvoice.setTimeInitialMedition(invoice.getTimeInitialMedition());
        }

        if(factura.getTimeFinalMedition() != null){
            newInvoice.setTimeFinalMedition(factura.getTimeFinalMedition());
        } else {
            newInvoice.setTimeFinalMedition(invoice.getTimeFinalMedition());
        }

        if(factura.getRate() != null){
            newInvoice.setRate(factura.getRate());
        } else {
            newInvoice.setRate(invoice.getRate());
        }

        if(factura.getTotal() != null){
            newInvoice.setTotal(factura.getTotal());
        } else {
            newInvoice.setTotal(invoice.getTotal());
        }

        if(factura.getPay() != null){
            newInvoice.setPay(factura.getPay());
        } else {
            newInvoice.setPay(invoice.getPay());
        }

        if(factura.getClient() != null){
            newInvoice.setClient(factura.getClient());
        } else {
            newInvoice.setClient(invoice.getClient());
        }

        return invoiceRepository.save(newInvoice);
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }
}
