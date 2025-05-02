package com.example.GestionDossiers.repository;

import com.example.GestionDossiers.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DossierRepository extends JpaRepository<Dossier, Long> {
    List<Dossier> findByStatut(String statut);
}