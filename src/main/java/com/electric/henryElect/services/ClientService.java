package com.electric.henryElect.services;

import com.electric.henryElect.model.Client;
import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.repository.AddressRepository;
import com.electric.henryElect.repository.ClientRepository;
import com.electric.henryElect.repository.InvoiceRepository;
import com.electric.henryElect.repository.LightMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ClientService {


    private ClientRepository clientRepository;
    private InvoiceService invoiceService;
    private ConversionService conversionService;

    @Autowired
    public ClientService(ClientRepository clientRepository, InvoiceService invoiceService) {
        this.clientRepository = clientRepository;
        this.invoiceService = invoiceService;
    }

    public Client getClientByID(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client fulano) {
        return clientRepository.save(fulano);
    }

    public Client editClient(Client fulano) {
        Client client = clientRepository.findById(fulano.getId())
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Client newClient = new Client();
        if(fulano.getDni() != null){
            newClient.setDni(fulano.getDni());
        } else {
            newClient.setDni(client.getDni());
        }

        if(fulano.getName() != null){
            newClient.setName(fulano.getName());
        } else {
            newClient.setName(client.getName());
        }

        if(fulano.getLastName() != null){
            newClient.setLastName(fulano.getLastName());
        } else {
            newClient.setLastName(client.getLastName());
        }

        return clientRepository.save(newClient);
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public void addInvoiceToClient(Integer id, Integer invoiceId) {
        Client client = getClientByID(id);
        Invoice invoice = invoiceService.getInvoiceByID(invoiceId);

        invoiceService.createInvoice(invoice);
        List<Invoice> listInvoice = client.getInvoices();
        listInvoice.add(invoice);
        client.setInvoices(listInvoice);
//        editClient(client);
    }
}
