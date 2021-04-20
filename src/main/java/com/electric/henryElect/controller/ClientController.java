package com.electric.henryElect.controller;

import com.electric.henryElect.model.Client;
import com.electric.henryElect.services.ClientService;
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
