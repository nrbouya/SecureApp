package com.example.GestionDossiers.service;

import com.example.GestionDossiers.model.Dossier;
import com.example.GestionDossiers.model.Historique;
import com.example.GestionDossiers.model.Utilisateur;
import com.example.GestionDossiers.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueService {
    @Autowired
    private HistoriqueRepository historiqueRepository;
    
    /**
     * Récupère tous les historiques
     */
    public List<Historique> getAllHistoriques() {
        return historiqueRepository.findAll();
    }
    
    /**
     * Récupère un historique par son identifiant
     */
    public Optional<Historique> getHistoriqueById(Long id) {
        return historiqueRepository.findById(id);
    }
    
    /**
     * Récupère les historiques d'un dossier
     */
    public List<Historique> getHistoriquesByDossier(Dossier dossier) {
        return historiqueRepository.findByDossierOrderByDateActionDesc(dossier);
    }
    
    /**
     * Récupère les historiques d'un utilisateur
     */
    public List<Historique> getHistoriquesByUtilisateur(Utilisateur utilisateur) {
        return historiqueRepository.findByUtilisateurOrderByDateActionDesc(utilisateur);
    }
    
    /**
     * Enregistre une action dans l'historique
     */
    public Historique enregistrerAction(Utilisateur utilisateur, Dossier dossier, String typeAction, String details) {
        Historique historique = new Historique();
        historique.setDateAction(new Date());
        historique.setTypeAction(typeAction);
        historique.setDetails(details);
        historique.setUtilisateur(utilisateur);
        historique.setDossier(dossier);
        
        return historiqueRepository.save(historique);
    }
    
    /**
     * Récupère les dernières actions dans l'historique (pour tableau de bord)
     */
    public List<Historique> getDernieresActions(int limit) {
        return historiqueRepository.findTop10ByOrderByDateActionDesc();
    }
}
