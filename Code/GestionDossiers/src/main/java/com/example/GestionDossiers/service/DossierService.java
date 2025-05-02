package com.example.GestionDossiers.service;

import com.example.GestionDossiers.model.Dossier;
import com.example.GestionDossiers.model.Historique;
import com.example.GestionDossiers.model.Utilisateur;
import com.example.GestionDossiers.repository.DossierRepository;
import com.example.GestionDossiers.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DossierService {
    @Autowired
    private DossierRepository dossierRepository;
    
    @Autowired
    private HistoriqueRepository historiqueRepository;
    
    /**
     * Récupère tous les dossiers
     */
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }
    
    /**
     * Récupère un dossier par son identifiant
     */
    public Optional<Dossier> getDossierById(Long id) {
        return dossierRepository.findById(id);
    }
    
    /**
     * Crée un nouveau dossier
     */
    @Transactional
    public Dossier creerDossier(Dossier dossier, Utilisateur utilisateur) {
        // Initialisation des métadonnées
        dossier.setDateCreation(new Date());
        dossier.setCreePar(utilisateur);
        dossier.setStatut("actif");
        
        // Sauvegarde en base
        Dossier dossierSauvegarde = dossierRepository.save(dossier);
        
        // Journalisation de l'action
        Historique historique = new Historique();
        historique.setDateAction(new Date());
        historique.setTypeAction("CREATION");
        historique.setDetails("Création du dossier " + dossier.getNom());
        historique.setUtilisateur(utilisateur);
        historique.setDossier(dossierSauvegarde);
        historiqueRepository.save(historique);
        
        return dossierSauvegarde;
    }
    
    /**
     * Modifie un dossier existant
     */
    @Transactional
    public Dossier modifierDossier(Long id, Dossier dossierDetails, Utilisateur utilisateur) {
        return dossierRepository.findById(id)
            .map(dossier -> {
                // Mise à jour des données
                dossier.setNom(dossierDetails.getNom());
                dossier.setDescription(dossierDetails.getDescription());
                dossier.setModifiePar(utilisateur);
                dossier.setDateModification(new Date());
                
                // Si changement de statut
                if (dossierDetails.getStatut() != null) {
                    dossier.setStatut(dossierDetails.getStatut());
                }
                
                // Sauvegarde en base
                Dossier dossierModifie = dossierRepository.save(dossier);
                
                // Journalisation de l'action
                Historique historique = new Historique();
                historique.setDateAction(new Date());
                historique.setTypeAction("MODIFICATION");
                historique.setDetails("Modification du dossier " + dossier.getNom());
                historique.setUtilisateur(utilisateur);
                historique.setDossier(dossierModifie);
                historiqueRepository.save(historique);
                
                return dossierModifie;
            })
            .orElse(null);
    }
    
    /**
     * Suppression logique d'un dossier (changement de statut à "archivé")
     */
    @Transactional
    public boolean supprimerDossier(Long id, Utilisateur utilisateur) {
        return dossierRepository.findById(id)
            .map(dossier -> {
                // Suppression logique
                dossier.setStatut("archivé");
                dossier.setModifiePar(utilisateur);
                dossier.setDateModification(new Date());
                dossierRepository.save(dossier);
                
                // Journalisation de l'action
                Historique historique = new Historique();
                historique.setDateAction(new Date());
                historique.setTypeAction("SUPPRESSION");
                historique.setDetails("Archivage du dossier " + dossier.getNom());
                historique.setUtilisateur(utilisateur);
                historique.setDossier(dossier);
                historiqueRepository.save(historique);
                
                return true;
            })
            .orElse(false);
    }
    
    /**
     * Récupère les dossiers par statut
     */
    public List<Dossier> getDossiersByStatut(String statut) {
        return dossierRepository.findByStatut(statut);
    }
}
