package com.facturation.facture.service.implementation;

import com.facturation.facture.dao.ClientDao;
import com.facturation.facture.model.Client;
import com.facturation.facture.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service métier pour les clients.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    /**
     * Récupère la liste de tous les clients.
     */
    @Override
    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    /**
     * Récupère un client par son identifiant.
     */
    @Override
    public Client getClientById(Long id) {
        return clientDao.findById(id);
    }

    /**
     * Crée un nouveau client.
     */
    @Override
    public Client createClient(Client client) {
        return clientDao.save(client);
    }
}
