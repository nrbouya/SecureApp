package com.example.GestionDossiers.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAction;

    private String typeAction; // "CREATION", "MODIFICATION", "SUPPRESSION"
    private String details;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Dossier dossier;

    public Historique() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getDateAction() { return dateAction; }
    public void setDateAction(Date dateAction) { this.dateAction = dateAction; }
    public String getTypeAction() { return typeAction; }
    public void setTypeAction(String typeAction) { this.typeAction = typeAction; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public Dossier getDossier() { return dossier; }
    public void setDossier(Dossier dossier) { this.dossier = dossier; }
}
