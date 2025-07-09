package com.facturation.facture.dao.implementation;


import com.facturation.facture.dao.FactureDao;
import com.facturation.facture.dao.JpaFactureDao;
import com.facturation.facture.model.Facture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FactureDaoImpl implements FactureDao {

    private final JpaFactureDao jpaFactureDao;

    // par  spring data usage d'interface GENERIQUE repositoryJPA l'injection sur DAO sera auto-implémenté
    public FactureDaoImpl(JpaFactureDao jpaFactureDao) {
        this.jpaFactureDao = jpaFactureDao;
    }

    @Override
    public List findAll() {
        return jpaFactureDao.findAll();
    }

    @Override
    public Facture findById(Long id) {
        return jpaFactureDao.findById(id).orElse(null);
    }

    @Override
    public Facture save(Facture facture) {
        return jpaFactureDao.save(facture);
    }
}