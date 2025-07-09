package com.facturation.facture.service;

import com.facturation.facture.dto.FactureCreationDTO;
import com.facturation.facture.dto.FactureExportDTO;
import com.facturation.facture.model.Facture;

import java.util.List;

public interface FactureService {

    /* Creation de new facture  après le  calcul des montants. */
    Facture createFacture(Facture facture);

    /*/trouver tous les factures */
    List<Facture> getAllFactures();


    Facture getFactureById(Long id);


    public Facture createFactureAvecDTO(FactureCreationDTO dto);

    List<FactureExportDTO> getAllFacturesExport();


    FactureExportDTO exporterFactureEnJSON(Long id);

}