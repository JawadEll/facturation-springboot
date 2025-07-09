package com.facturation.facture.dao;


import com.facturation.facture.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*interface Spring Data JPA pour Client
pour  ajouter des méthodes personnalisées
 */
@Repository
public interface JpaClientDao extends JpaRepository<Client, Long> {
}
