package com.example.GestionDossiers.repository;


import com.example.GestionDossiers.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
    List<Historique> findByDossierOrderByDateActionDesc(Dossier dossier);
    
    // La méthode pour trouver par utilisateur mentionnée dans ton service
    List<Historique> findByUtilisateurOrderByDateActionDesc(Utilisateur utilisateur);
    
    // La méthode pour les 10 dernières actions
    List<Historique> findTop10ByOrderByDateActionDesc();
}