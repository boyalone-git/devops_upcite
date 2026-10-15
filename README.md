# Solidarité Arrivants - Plateforme d'Orientation et d'Entraide

## Description du Projet
Cette plateforme met en relation les personnes primo-arrivantes (ou en situation administrative précaire) avec les associations d'aide locale, tout en permettant aux citoyens de proposer des missions de bénévolat (traduction, accompagnement administratif, hébergement d'urgence). 

## Architecture Technique
Projet développé selon une architecture orientée microservices :
- **Service 1 (Annuaire)** : API REST gérant la liste des associations et des démarches (Java / Spring Boot).
- **Service 2 (Bénévoles)** : API gérant les profils des aidants (Java / Spring Boot).
- **Conteneurisation** : Docker & Docker Hub.
- **Orchestration** : Kubernetes (Minikube).
- **Routage & Sécurité** : API Gateway / Istio Service Mesh, RBAC (À venir).

## Lancement Rapide (Local)
1. Cloner le dépôt : `git clone [URL_DU_DEPOT]`
2. Déployer sur Kubernetes : `kubectl apply -f k8s/`