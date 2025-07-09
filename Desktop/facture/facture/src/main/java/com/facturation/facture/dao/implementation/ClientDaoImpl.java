package com.facturation.facture.dao.implementation;

import com.facturation.facture.dao.ClientDao;
import com.facturation.facture.dao.JpaClientDao;
import org.springframework.stereotype.Repository;

// par  spring data usage d'interface GENERIQUE repositoryJPA l'injection sur DAO sera auto-implémenté

import com.facturation.facture.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

    /**
     * Implémentation personnalisée de ClientDao.
     */
    @Repository
    public class ClientDaoImpl implements ClientDao {

        private final JpaClientDao jpaClientDao;

        public ClientDaoImpl(JpaClientDao jpaClientDao) {
            this.jpaClientDao = jpaClientDao;
        }

        /**
         * Retourne tous les clients.
         */
        @Override
        public List<Client> findAll() {
            return jpaClientDao.findAll();
        }

        /** recherche  le  client par son id*/
        @Override
        public Client findById(Long id) {
            return jpaClientDao.findById(id).orElse(null);
        }

        //pour ajouter un client en db base de donner
        @Override
        public Client save(Client client) {
            return jpaClientDao.save(client);
        }
    }


