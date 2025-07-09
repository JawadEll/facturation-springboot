package com.facturation.facture.controller;



import com.facturation.facture.model.Client;

import com.facturation.facture.service.ClientService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController

@RequestMapping("/clients/api")

@Tag(name = "Client", description = "Gestion des clients")

public class ClientController {



    private final ClientService clientService;



    // Constructeur classique, rien de spécial ici

    public ClientController(ClientService clientService) {

        this.clientService = clientService;

    }



    // Récupère tous les clients (sans filtre ni pagination pour l'instant)

    @GetMapping

    public List<Client> getAllClients() {

        return clientService.getAllClients();

    }



    // Cherche un client via son identifiant (retourne null s'il n'existe pas)

    @GetMapping("/{id}")

    public Client getClientById(@PathVariable Long id) {

        return clientService.getClientById(id);

    }



    // Ajoute un nouveau client - le JSON envoyé doit correspondre au modèle Client

    @PostMapping

    public Client createClient(@RequestBody Client client) {

        return clientService.createClient(client);

    }

}

