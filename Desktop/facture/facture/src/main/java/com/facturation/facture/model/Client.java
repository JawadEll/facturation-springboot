package com.facturation.facture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom ;
    private String email ;
    private String siret ;
    private LocalDate dateCreation ;


    @OneToMany(mappedBy = "client" ,
            cascade = CascadeType.ALL)
    // pour Eviter Les boucles
    @ToString.Exclude
    @JsonIgnore
    private List<Facture> factures ;



}