package com.example.GestionDossiers.controller;

import com.example.GestionDossiers.model.Dossier;
import com.example.GestionDossiers.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossiers")
public class DossierController {

    @Autowired
    private DossierService dossierService;

    @GetMapping
    public List<Dossier> getAllDossiers() {
        return dossierService.getAllDossiers();
    }

    @GetMapping("/{id}")
    public Dossier getDossierById(@PathVariable Long id) {
        return dossierService.getDossierById(id).orElse(null);
    }

    @PostMapping
    public Dossier createDossier(@RequestBody Dossier dossier) {
        // Ici, il faudrait passer l'utilisateur connecté si tu gères l'authentification
        return dossierService.creerDossier(dossier, null);
    }

    @PutMapping("/{id}")
    public Dossier updateDossier(@PathVariable Long id, @RequestBody Dossier dossier) {
        // Ici aussi, il faudrait passer l'utilisateur connecté
        return dossierService.modifierDossier(id, dossier, null);
    }

    @DeleteMapping("/{id}")
    public void deleteDossier(@PathVariable Long id) {
        dossierService.supprimerDossier(id, null);
    }
}
