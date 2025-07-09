# 📄 Module de Facturation – Spring Boot

> Une application backend REST pour gérer des clients, des factures et exporter les données (JSON).

---

## outil utilisée

- Java 17  
-  Spring Boot 3  
- Spring Data JPA  
- usage   => H2 (en prod)  
- Swagger (doc auto des endpoints)  pour UI 
- Maven  injectio des dependance demmander selon les critéres

---

##  Architecture 3-Tiers

Le projet est structuré en **trois couches logiques** :

### 1. **Controller (Web Layer)**  
Sert à exposer les endpoints de l’API REST (HTTP).  

Exemple : `@GetMapping("/factures")` reçoit une requête HTTP et renvoie la réponse correspondante.

### 2. **Service (Business Layer)**  

 Contient la logique métier.  
Ici, on gère les règles comme le calcul du montant TTC d’une facture ou la validation du taux de TVA.

### 3. **DAO / Repository (Data Access Layer)**  
 Interagit directement avec la base de données via Spring Data JPA.  
Exemples : `FactureDao.findAll()`, `ClientDao.save()`.

---

##  Structure du projet

com.facturation.facture
      controller # API REST : expose les endpoints
      
      service # Interfaces métier
      service.implementation # Logique métier (implémentations)
      dao # Repositories Spring JPA
      model # Entités JPA (Client, Facture, LigneFacture)
      dto # Objets de transfert (DTO)
      config # Configuration Swagger  


      
---

##  Fonctionnalités principales

### Clients
- `GET /clients` – Liste tous les clients  
- `GET /clients/{id}` – Détails d’un client  
- `POST /clients` – Créer un nouveau client  

###  Factures
- `GET /factures` – Liste des factures  
- `GET /factures/{id}` – Détail d’une facture  
- `POST /factures` – Créer une facture avec calcul automatique  
- `POST /factures/dto` – Créer une facture via DTO (client + lignes)  
- `GET /factures/{id}/export` – Export d’une facture en JSON  
- `GET /factures/export/all` – Export de toutes les factures  

---

##  Exemple de création de facture (JSON)

```json
POST /factures/dto

{
  "date": "2025-07-09",
  "client": {
    "nom": "Société ABC",
    "email": "abc@example.com",
    "siret": "12345678901234"
  },
  "lignes": [
    {
      "description": "Service A",
      "quantite": 2,
      "prixUnitaireHT": 150.0,
      "tauxTVA": 20.0
    },
    {
      "description": "Produit B",
      "quantite": 1,
      "prixUnitaireHT": 300.0,
      "tauxTVA": 10.0
    }
  ]
}
