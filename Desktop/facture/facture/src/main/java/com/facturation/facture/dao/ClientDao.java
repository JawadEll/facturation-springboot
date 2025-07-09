package com.facturation.facture.dao;

import com.facturation.facture.model.Client;

import java.util.List;


    public interface ClientDao {
        List<Client> findAll();
        Client findById(Long id);
        Client save(Client client);
    }

