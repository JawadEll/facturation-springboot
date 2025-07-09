package com.facturation.facture.dto;

import java.time.LocalDate;
import java.util.List;

// Utilisé pour envoyer les infos complètes d'une facture (client + lignes + totaux)
public class FactureExportDTO {

    private Long factureId;
    private LocalDate date;
    private ClientInfo client;
    private List<LigneInfo> lignes;
    private double totalHT;
    private double totalTVA;
    private double totalTTC;

    // Classe représentant les infos du client lié à la facture
    public static class ClientInfo {
        private String nom;
        private String email;
        private String siret;

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSiret() {
            return siret;
        }

        public void setSiret(String siret) {
            this.siret = siret;
        }
    }

    // Ligne d'article/service dans la facture
    public static class LigneInfo {
        private String description;
        private int quantite;
        private double prixUnitaireHT;
        private double tauxTVA;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getQuantite() {
            return quantite;
        }

        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }

        public double getPrixUnitaireHT() {
            return prixUnitaireHT;
        }

        public void setPrixUnitaireHT(double prixUnitaireHT) {
            this.prixUnitaireHT = prixUnitaireHT;
        }

        public double getTauxTVA() {
            return tauxTVA;
        }

        public void setTauxTVA(double tauxTVA) {
            this.tauxTVA = tauxTVA;
        }
    }

    // Accesseurs de la facture
    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ClientInfo getClient() {
        return client;
    }

    public void setClient(ClientInfo client) {
        this.client = client;
    }

    public List<LigneInfo> getLignes() {
        return lignes;



    }

    public void setLignes(List<LigneInfo> lignes) {
        this.lignes = lignes;


                      }

    public double getTotalHT() {
        return totalHT;
    }
//seeter
    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public double getTotalTVA()
    {
        return totalTVA;}



    public void setTotalTVA(double totalTVA) {
        this.totalTVA = totalTVA;}

//geter

    public double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
    }
}
