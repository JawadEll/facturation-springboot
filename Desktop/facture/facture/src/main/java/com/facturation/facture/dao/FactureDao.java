package com.facturation.facture.dao;
import com.facturation.facture.model.Facture;

import java.util.List;

public interface FactureDao {
// fonction crud usage couche DAO pour organiser les requetes vers la base de données
    // chercher Tous (all)
    List<Facture> findAll();
        //chercher via ID
        Facture findById(Long id);
        //ajout
        Facture save(Facture facture);
    }


