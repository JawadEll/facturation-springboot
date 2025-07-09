package com.facturation.facture.dto;

import java.time.LocalDate;
import java.util.List;

public class FactureCreationDTO {

    private LocalDate date;
    private ClientDTO client;
    private List<LigneFactureDTO> lignes;

    private double totalHT;
    private double totalTVA;
    private double totalTTC;

    // -------------- Client DTO
    public static class ClientDTO {
        public String nom;
        public String email;
        public String siret;

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

    // === Ligne DTO ===
    public static class LigneFactureDTO {
        public String description;
        public int quantite;
        public double prixUnitaireHT;
        public double tauxTVA;

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

    // === Getters/Setters ===
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<LigneFactureDTO> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneFactureDTO> lignes) {
        this.lignes = lignes;
    }

    public double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public double getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(double totalTVA) {
        this.totalTVA = totalTVA;
    }

    public double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
    }
}
