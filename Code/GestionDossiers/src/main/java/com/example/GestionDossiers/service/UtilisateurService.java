package com.example.GestionDossiers.service;

import com.example.GestionDossiers.model.Utilisateur;
import com.example.GestionDossiers.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    /**
     * Récupère tous les utilisateurs
     */
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
    
    /**
     * Récupère un utilisateur par son identifiant
     */
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }
    
    /**
     * Récupère un utilisateur par son email
     */
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    
    /**
     * Crée un nouvel utilisateur
     */
    @Transactional
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        // Validation de l'email unique
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        
        
        // Initialisation du statut
        utilisateur.setStatut("actif");
        
        return utilisateurRepository.save(utilisateur);
    }
    
    /**
     * Modifie un utilisateur existant
     */
    @Transactional
    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateurDetails) {
        return utilisateurRepository.findById(id)
            .map(utilisateur -> {
                // Mise à jour des informations
                utilisateur.setNom(utilisateurDetails.getNom());
                utilisateur.setPrenom(utilisateurDetails.getPrenom());
                
                // Modification de l'email (si fourni)
                if (utilisateurDetails.getEmail() != null && !utilisateurDetails.getEmail().isEmpty()) {
                    // Vérification de l'unicité de l'email
                    Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(utilisateurDetails.getEmail());
                    if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                        throw new RuntimeException("Cet email est déjà utilisé");
                    }
                    utilisateur.setEmail(utilisateurDetails.getEmail());
                }
                
                // Modification du mot de passe (si fourni)
              
                // Mise à jour du rôle (si autorisé)
                if (utilisateurDetails.getRole() != null) {
                    utilisateur.setRole(utilisateurDetails.getRole());
                }
                
                // Mise à jour du statut
                if (utilisateurDetails.getStatut() != null) {
                    utilisateur.setStatut(utilisateurDetails.getStatut());
                }
                
                return utilisateurRepository.save(utilisateur);
            })
            .orElse(null);
    }
    
    /**
     * Suppression logique d'un utilisateur (changement de statut à "inactif")
     */
    @Transactional
    public boolean supprimerUtilisateur(Long id) {
        return utilisateurRepository.findById(id)
            .map(utilisateur -> {
                // Suppression logique
                utilisateur.setStatut("inactif");
                utilisateurRepository.save(utilisateur);
                return true;
            })
            .orElse(false);
    }
    
    /**
     * Authentifie un utilisateur
     */
    public boolean authentifier(String email, String motDePasse) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        
        return false;
    }
}
