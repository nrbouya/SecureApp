package com.example.GestionDossiers.controller;

import com.example.GestionDossiers.model.Historique;
import com.example.GestionDossiers.model.Dossier;
import com.example.GestionDossiers.model.Utilisateur;
import com.example.GestionDossiers.service.HistoriqueService;
import com.example.GestionDossiers.service.DossierService;
import com.example.GestionDossiers.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiques")
public class HistoriqueController {

    @Autowired
    private HistoriqueService historiqueService;
    @Autowired
    private DossierService dossierService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Historique> getAllHistoriques() {
        return historiqueService.getAllHistoriques();
    }

    @GetMapping("/{id}")
    public Historique getHistoriqueById(@PathVariable Long id) {
        return historiqueService.getHistoriqueById(id).orElse(null);
    }

    @GetMapping("/dossier/{dossierId}")
    public List<Historique> getHistoriquesByDossier(@PathVariable Long dossierId) {
        return dossierService.getDossierById(dossierId)
                .map(historiqueService::getHistoriquesByDossier)
                .orElse(null);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Historique> getHistoriquesByUtilisateur(@PathVariable Long utilisateurId) {
        return utilisateurService.getUtilisateurById(utilisateurId)
                .map(historiqueService::getHistoriquesByUtilisateur)
                .orElse(null);
    }

    @GetMapping("/recents")
    public List<Historique> getDernieresActions() {
        return historiqueService.getDernieresActions(10);
    }
}
