package com.facturation.facture.service.implementation;

import com.facturation.facture.dao.ClientDao;
import com.facturation.facture.dao.FactureDao;
import com.facturation.facture.dao.LigneFactureDao;
import com.facturation.facture.dto.FactureCreationDTO;
import com.facturation.facture.dto.FactureExportDTO;
import com.facturation.facture.model.Client;
import com.facturation.facture.model.Facture;
import com.facturation.facture.model.LigneFacture;
import com.facturation.facture.service.FactureService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureDao factureDao;
    private final ClientDao clientDao;
    private final LigneFactureDao ligneFactureDao;

    public FactureServiceImpl(FactureDao factureDao, ClientDao clientDao, LigneFactureDao ligneFactureDao) {
        this.factureDao = factureDao;
        this.clientDao = clientDao;
        this.ligneFactureDao = ligneFactureDao;
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureDao.findAll();
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureDao.findById(id);
    }

    @Override
    public Facture createFacture(Facture facture) {
        if (facture.getLignes() == null || facture.getLignes().isEmpty()) {
            throw new IllegalArgumentException("Une facture doit contenir au moins une ligne.");
        }

        double totalHT = 0.0;
        double totalTVA = 0.0;

        for (LigneFacture ligne : facture.getLignes()) {
            double ligneHT = ligne.getPrixUnitaireHT() * ligne.getQuantite();
            double tauxTVA = ligne.getTauxTVA();

            if (!(tauxTVA == 0.0 || tauxTVA == 5.5 || tauxTVA == 10.0 || tauxTVA == 20.0)) {
                throw new IllegalArgumentException("Taux de TVA invalide : " + tauxTVA);
            }

            double ligneTVA = ligneHT * (tauxTVA / 100);
            totalHT += ligneHT;
            totalTVA += ligneTVA;
            ligne.setFacture(facture);
        }

        facture.setTotalHT(totalHT);
        facture.setTotalTVA(totalTVA);
        facture.setTotalTTC(totalHT + totalTVA);

        return factureDao.save(facture);
    }

    @Override
    public Facture createFactureAvecDTO(FactureCreationDTO dto) {
        Client client = new Client();
        client.setNom(dto.getClient().nom);
        client.setEmail(dto.getClient().email);
        client.setSiret(dto.getClient().siret);
        client.setDateCreation(LocalDate.now());
        clientDao.save(client);

        Facture facture = new Facture();
        facture.setDate(dto.getDate());
        facture.setClient(client);

        List<LigneFacture> lignes = new ArrayList<>();
        double totalHT = 0.0;
        double totalTVA = 0.0;

        for (FactureCreationDTO.LigneFactureDTO l : dto.getLignes()) {
            LigneFacture ligne = new LigneFacture();
            ligne.setDescription(l.description);
            ligne.setQuantite(l.quantite);
            ligne.setPrixUnitaireHT(l.prixUnitaireHT);
            ligne.setTauxTVA(l.tauxTVA);

            double ligneHT = l.prixUnitaireHT * l.quantite;
            if (!(l.tauxTVA == 0.0 || l.tauxTVA == 5.5 || l.tauxTVA == 10.0 || l.tauxTVA == 20.0)) {
                throw new IllegalArgumentException("Taux de TVA invalide : " + l.tauxTVA);
            }

            double ligneTVA = ligneHT * (l.tauxTVA / 100.0);
            totalHT += ligneHT;
            totalTVA += ligneTVA;

            ligne.setFacture(facture);
            lignes.add(ligne);
        }

        facture.setLignes(lignes);
        facture.setTotalHT(totalHT);
        facture.setTotalTVA(totalTVA);
        facture.setTotalTTC(totalHT + totalTVA);

        Facture savedFacture = factureDao.save(facture);
        ligneFactureDao.saveAll(lignes);

        return savedFacture;
    }

    @Override
    public FactureExportDTO exporterFactureEnJSON(Long id) {
        Facture facture = factureDao.findById(id);
        if (facture == null) return null;

        FactureExportDTO dto = new FactureExportDTO();
        dto.setFactureId(facture.getId());
        dto.setDate(facture.getDate());
        dto.setTotalHT(facture.getTotalHT());
        dto.setTotalTVA(facture.getTotalTVA());
        dto.setTotalTTC(facture.getTotalTTC());

        FactureExportDTO.ClientInfo clientInfo = new FactureExportDTO.ClientInfo();
        clientInfo.setNom(facture.getClient().getNom());
        clientInfo.setEmail(facture.getClient().getEmail());
        clientInfo.setSiret(facture.getClient().getSiret());
        dto.setClient(clientInfo);

        List<FactureExportDTO.LigneInfo> ligneDTOs = new ArrayList<>();
        for (LigneFacture l : facture.getLignes()) {
            FactureExportDTO.LigneInfo ligne = new FactureExportDTO.LigneInfo();
            ligne.setDescription(l.getDescription());
            ligne.setQuantite(l.getQuantite());
            ligne.setPrixUnitaireHT(l.getPrixUnitaireHT());
            ligne.setTauxTVA(l.getTauxTVA());
            ligneDTOs.add(ligne);
        }
        dto.setLignes(ligneDTOs);

        return dto;
    }

    @Override
    public List<FactureExportDTO> getAllFacturesExport() {
        List<Facture> factures = factureDao.findAll();
        List<FactureExportDTO> exportList = new ArrayList<>();

        for (Facture facture : factures) {
            Client client = facture.getClient();

            FactureExportDTO.ClientInfo clientInfo = new FactureExportDTO.ClientInfo();
            clientInfo.setNom(client.getNom());
            clientInfo.setEmail(client.getEmail());
            clientInfo.setSiret(client.getSiret());

            List<FactureExportDTO.LigneInfo> ligneInfos = new ArrayList<>();
            for (LigneFacture ligne : facture.getLignes()) {
                FactureExportDTO.LigneInfo info = new FactureExportDTO.LigneInfo();
                info.setDescription(ligne.getDescription());
                info.setQuantite(ligne.getQuantite());
                info.setPrixUnitaireHT(ligne.getPrixUnitaireHT());
                info.setTauxTVA(ligne.getTauxTVA());
                ligneInfos.add(info);
            }

            FactureExportDTO dto = new FactureExportDTO();
            dto.setFactureId(facture.getId());
            dto.setDate(facture.getDate());
            dto.setClient(clientInfo);
            dto.setLignes(ligneInfos);
            dto.setTotalHT(facture.getTotalHT());
            dto.setTotalTVA(facture.getTotalTVA());
            dto.setTotalTTC(facture.getTotalTTC());

            exportList.add(dto);
        }

        return exportList;
    }
}
