package com.facturation.facture.service;

import com.facturation.facture.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client createClient(Client client);

}
