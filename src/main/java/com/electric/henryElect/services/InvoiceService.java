package com.electric.henryElect.services;


import com.electric.henryElect.dtos.InvoiceDTO;
import com.electric.henryElect.model.Address;
import com.electric.henryElect.model.Client;
import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.repository.ClientRepository;
import com.electric.henryElect.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private ClientService clientService;
    private AddressService addressService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ClientService clientService) {
        this.invoiceRepository = invoiceRepository;
        this.clientService = clientService;
    }



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

        if (factura.getClient() != null){
            newInvoice.setClient(factura.getClient());
        } else {
            factura.setClient(invoice.getClient());
        }

        return invoiceRepository.save(newInvoice);
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice addClientToInvoice(Integer invoiceid, Integer clientid) {
        Invoice invoice = getInvoiceByID(invoiceid);
        Client client = clientService.getClientByID(clientid);

        invoice.setClient(client);
        editInvoice(invoice);
        return invoice;
    }

    public InvoiceDTO getConsumeByDate(LocalDateTime init, LocalDateTime end, Integer userid) {
        Client client = clientService.getClientByID(userid);
        List<Invoice> lintInvoices = client.getInvoices();
        Double consume = 0.0;
        Double kwh = 0.0;
        for (Invoice i: lintInvoices){
            if(i.getTimeInitialMedition().isAfter(init) && i.getTimeFinalMedition().isBefore(end)){
                consume += i.getTotal();
                kwh += i.getTotal() / i.getRate();
            }
        }

        InvoiceDTO newInvoiceDTO = new InvoiceDTO();
        newInvoiceDTO.setTotal(consume);
        newInvoiceDTO.setKwh(kwh);
        newInvoiceDTO.setTimeFinalMedition(end);
        newInvoiceDTO.setTimeInitialMedition(init);

        return newInvoiceDTO;
    }

    public List<Double> getMeditionByDate(LocalDateTime init, LocalDateTime end, Integer clientid) {
        Client client = clientService.getClientByID(clientid);
        List<Invoice> lintInvoices = client.getInvoices();
        List<Double> listKwh = null;
        for(Invoice i: lintInvoices){
            if(i.getTimeInitialMedition().isAfter(init) && i.getTimeFinalMedition().isBefore(end)){
                listKwh.add(i.getTotal() / i.getRate());
            }
        }
        return listKwh;
    }

    public List<Double> getAddressMeditions(Integer addressid) {
        Address address = addressService.getAddressByID(addressid);
        Client client = invoiceRepository.findClientByAddress(address);
        List<Invoice> invoiceList = client.getInvoices();
        List<Double> meditionsList = new ArrayList<>();
        for(Invoice i: invoiceList){
            meditionsList.add(i.getTotal() / i.getRate());
        }

        return meditionsList;
    }
}
