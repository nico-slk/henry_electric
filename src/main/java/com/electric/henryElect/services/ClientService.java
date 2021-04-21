package com.electric.henryElect.services;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.model.Client;
import com.electric.henryElect.model.Invoice;
import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.repository.AddressRepository;
import com.electric.henryElect.repository.ClientRepository;
import com.electric.henryElect.repository.InvoiceRepository;
import com.electric.henryElect.repository.LightMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ClientService {


    private ClientRepository clientRepository;
    private LightMeterRepository lightMeterRepository;
    private InvoiceRepository invoiceRepository;
    private AddressRepository addressRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, LightMeterRepository lightMeterRepository, InvoiceRepository invoiceRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.lightMeterRepository = lightMeterRepository;
        this.invoiceRepository = invoiceRepository;
        this.addressRepository = addressRepository;
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

        if(fulano.getAddressid() != null){
            newClient.setAddressid(fulano.getAddressid());
        } else {
            newClient.setAddressid(client.getAddressid());
        }

        if(fulano.getInvoiceId() != null){
            newClient.setInvoiceId(fulano.getInvoiceId());
        } else {
            newClient.setInvoiceId(client.getInvoiceId());
        }

        if(fulano.getLightMeterId() != null){
            newClient.setLightMeterId(fulano.getLightMeterId());
        } else {
            newClient.setLightMeterId(client.getLightMeterId());
        }

        return clientRepository.save(newClient);
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public void addLightMeterToClient(Integer id, Integer lightmeterid) {
        LightMeter lightMeter = lightMeterRepository.findById(lightmeterid)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Client client = getClientByID(id);
        List<Integer> ids = client.getLightMeterId();
        Boolean done = ids.add(lightmeterid);
        client.setLightMeterId(ids);
    }

    public void addInvoiceToClient(Integer id, Integer invoiceid) {
        Invoice invoice = invoiceRepository.findById(invoiceid)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Client client = getClientByID(id);
        List<Integer> ids = client.getInvoiceId();
        Boolean done = ids.add(invoiceid);
        client.setInvoiceId(ids);
        invoice.setClient(client);
    }

    public void addAddressToClient(Integer id, Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Client client = getClientByID(id);
        client.setAddressid(addressId);
        address.setClientId(id);
    }
}
