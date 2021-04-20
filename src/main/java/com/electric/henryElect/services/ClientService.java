package com.electric.henryElect.services;

import com.electric.henryElect.model.Client;
import com.electric.henryElect.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
        Client client = clientRepository.findById(fulano.getDni())
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Client newClient = new Client();
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

        if(fulano.getAddress() != null){
            newClient.setAddress(fulano.getAddress());
        } else {
            newClient.setAddress(client.getAddress());
        }

        if(fulano.getInvoice() != null){
            newClient.setInvoice(fulano.getInvoice());
        } else {
            newClient.setInvoice(client.getInvoice());
        }

        if(fulano.getLightMeter() != null){
            newClient.setLightMeter(fulano.getLightMeter());
        } else {
            newClient.setLightMeter(client.getLightMeter());
        }

        return clientRepository.save(newClient);
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
