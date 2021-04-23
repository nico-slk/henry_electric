package com.electric.henryElect.services;

import com.electric.henryElect.model.Client;
import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


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

        if(fulano.getInvoices() != null){
            newClient.setInvoices(fulano.getInvoices());
        } else {
            newClient.setInvoices(client.getInvoices());
        }

        return clientRepository.save(newClient);
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public List<Invoice> getUnpaidInvoices(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        List<Invoice> invoicesList = client.getInvoices();
        List<Invoice> unPaidList =  new ArrayList<>();
        for(Invoice i: invoicesList){
            if(!i.getPay() && unPaidList.size() < 10){
                unPaidList.add(i);
            }
        }
        return unPaidList;
    }

    public List<Client> getMostConsumer() {
        List<Client> clients = getAllClients();
        List<Invoice> invoiceList = new ArrayList<>();
        for(Client c: clients){
            List<Invoice> listaFacturas = c.getInvoices();
            Integer ultimaFactura = listaFacturas.size() - 1;
            invoiceList.add(listaFacturas.get(ultimaFactura));
        }

        invoiceList.sort(Comparator.comparing(Invoice::getTotal));

        List<Client> moreConsumer = new ArrayList<>();

        for (Integer i = 0; i < 9; i++) {
            Client c = getClientByID(invoiceList.get(i).getId());
            moreConsumer.add(c);
        }

        return moreConsumer;
    }
}
