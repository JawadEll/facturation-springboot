package com.facturation.facture.dao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.facturation.facture.model.Facture;

@Repository
public interface JpaFactureDao extends JpaRepository<Facture, Long> {
    // ajouter des méthodes personnalisées


}

