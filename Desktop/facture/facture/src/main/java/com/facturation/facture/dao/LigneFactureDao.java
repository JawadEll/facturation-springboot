package com.facturation.facture.dao;

import com.facturation.facture.model.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneFactureDao extends JpaRepository<LigneFacture, Long> {
    // on  peux  ajouter     ici des méthodes personnalisées si nécessaire
}
