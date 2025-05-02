# Gestion sécurisée des dossiers administratifs

Application web Java/Spring Boot pour la gestion des dossiers administratifs d'une collectivité territoriale, développée selon les standards de sécurité ANSSI et les exigences RGPD.

## 📋 Description du projet

Cette application permet aux agents et responsables d'une collectivité territoriale de gérer de manière sécurisée les dossiers administratifs :
- Création, consultation, modification et archivage de dossiers
- Gestion des utilisateurs et des droits d'accès
- Traçabilité complète des actions (audit log)
- Interface accessible et conforme RGAA

Le projet a été développé selon une architecture multicouche sécurisée, avec une attention particulière portée à la protection des données et à la conformité réglementaire.

## 🛠️ Technologies utilisées

- **Backend** : Java 11, Spring Boot 2.7, Spring Security, Spring Data JPA
- **Base de données** : MySQL
- **Sécurité** : Authentification basée sur les rôles, hachage bcrypt, protection CSRF, validation des entrées

## 🔒 Fonctionnalités de sécurité

- Authentification forte et gestion des sessions sécurisée
- Contrôle d'accès basé sur les rôles (RBAC)
- Chiffrement des données sensibles
- Protection contre les injections SQL et XSS
- Journalisation complète des actions (audit log)
- Conformité RGPD (droit à l'oubli, minimisation des données)


