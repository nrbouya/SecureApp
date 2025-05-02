package com.example.GestionDossiers.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String statut; // "actif", "archivé"

    @ManyToOne
    @JoinColumn(name = "cree_par")
    private Utilisateur creePar;

    @ManyToOne
    @JoinColumn(name = "modifie_par")
    private Utilisateur modifiePar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;

    public Dossier() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public Utilisateur getCreePar() { return creePar; }
    public void setCreePar(Utilisateur creePar) { this.creePar = creePar; }
    public Utilisateur getModifiePar() { return modifiePar; }
    public void setModifiePar(Utilisateur modifiePar) { this.modifiePar = modifiePar; }
    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    public Date getDateModification() { return dateModification; }
    public void setDateModification(Date dateModification) { this.dateModification = dateModification; }
}
