package com.electric.henryElect.controller;

import com.electric.henryElect.model.Client;
import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.services.ClientService;
import com.electric.henryElect.services.LightMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Integer id){
        return clientService.getClientByID(id);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    // Acá agregamos el ID del medidor al cliente y al medidor le seteamos el ID del cliente
    @PostMapping("/{id}/lightmeter/{lightmeterid}")
    public String postLightMeterToClient(@PathVariable Integer id, @PathVariable Integer lightmeterid){
        clientService.addLightMeterToClient(id, lightmeterid);
        return ("Medidor con id " + lightmeterid + " ha sido agregado en el cliente con id " + id);
    }

    // Acá agregamos el ID de la factura al cliente y a factura le seteamos el cliente
    @PostMapping("/{id}/invoice/{invoiceId}")
    public String postInvoiceToClient(@PathVariable Integer id, @PathVariable Integer invoiceid){
        clientService.addInvoiceToClient(id, invoiceid);
        return ("Factura con id " + invoiceid + " ha sido agregado en el cliente con id " + id);
    }

    // Acá agregamos el ID del domicilio y a domicilio le seteamos el ID del cliente
    @PostMapping("/{id}/address/{addressId}")
    public String postAddressToClient(@PathVariable Integer id, @PathVariable Integer addressId){
        clientService.addAddressToClient(id, addressId);
        return ("Dirección con id " + addressId + " ha sido agregado en el cliente con id " + id);
    }

    @PostMapping
    public String postClient(@RequestBody Client fulano){
        Client client = clientService.addClient(fulano);
        return ("Cliente " + client + " ha sido creado.");
    }

    @PutMapping
    public String putClient(@RequestBody Client fulano){
        Client client = clientService.editClient(fulano);
        return ("Cliente " + client + " ha sido modificado.");
    }

    @DeleteMapping("/{id}")
    public String delClient(@PathVariable Integer id){
        clientService.deleteClient(id);
        return ("Cliente con id " + id + " ha sido exterminado.");
    }

}
